package service;

import controller.WebSocketController;
import entity.Game;
import entity.Hook;
import entity.Map;
import entity.Tribute;
import model.Message;
import model.TributeHealth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class SchedulerService {

    private final GameService gameService;
    private final TributeService tributeService;
    private final HookService hookService;
    private final MapService mapService;
    private final WebSocketController webSocketController;
    private final WeaponsInGameService weaponsInGameService;
    private final GameProcessService gameProcessService;
    private final SessionRegistry sessionRegistry;
    private final UserService userService;

    private Game gameToday;
    private List<Tribute> tributesToday;

    @Autowired
    public SchedulerService(GameService gameService, TributeService tributeService, HookService hookService, MapService mapService, WebSocketController webSocketController, WeaponsInGameService weaponsInGameService, GameProcessService gameProcessService, SessionRegistry sessionRegistry, UserService userService) {
        this.gameService = gameService;
        this.tributeService = tributeService;
        this.hookService = hookService;
        this.mapService = mapService;
        this.webSocketController = webSocketController;
        this.weaponsInGameService = weaponsInGameService;
        this.gameProcessService = gameProcessService;
        this.sessionRegistry = sessionRegistry;
        this.userService = userService;
    }

    @Scheduled(cron = "0 01 1 * * *")
    public void prepareMap(){
        init();
        if (gameToday!=null) {
            mapService.createAllGameField(gameToday.getArena());
            weaponsInGameService.throwWeaponsOnArena(gameToday);
        }
    }

    @Scheduled(cron = "0 50 9 * * *")
    public void prepareTributes(){
        init();
        if (gameToday!=null) {
            final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
            List<entity.User> usersOnline = new ArrayList<>();

            for(final Object principal : allPrincipals) {
                if(principal instanceof org.springframework.security.core.userdetails.User) {
                    usersOnline.add(userService.getUserByNick(((org.springframework.security.core.userdetails.User)principal).getUsername()));
                }
            }
            gameProcessService.selection(usersOnline, gameToday);
        }
    }

    @Scheduled(cron = "0 0 10 * * *")
    public void runGame(){
        init();
        if (gameToday!=null) {
            gameToday.setStatus("running");
            gameService.updateGame(gameToday);
            webSocketController.gameEvent(new Message("Начались " + gameToday.getGameId() + " Голодные игры", "", Message.Type.GAMESTART));
        }
    }

    @Scheduled(cron = "0 */5 10-23 * * *")
    public void decreaseTributesHunger(){
        init();
        if (tributesToday.size()!=0) {
            for (Tribute tribute : tributesToday) {
                tributeService.getHunger(tribute, 1);
                tributeService.getThirst(tribute, 2);
                webSocketController.getHealth(new TributeHealth(tribute.getUser().getNick(),tribute.getHealth(), tribute.getHunger(), tribute.getThirst()));
            }
        }
    }

    @Scheduled(cron = "0 */10 10-23 * * *")
    public void decreaseTributesHealth(){
        int damage, x, y;
        init();
        if (tributesToday.size()!=0) {
            for (Tribute tribute : tributesToday) {
                x = tribute.getLocationX(); y = tribute.getLocationY();
                damage = ((100 - tribute.getHunger()) + (100 - tribute.getThirst()))/10;
                tributeService.getDamage(tribute, damage);
                if (tribute.getHealth()<=0){
                    webSocketController.gameEvent(new Message(tribute.getUser().getNick()+", "+tribute.getUser().getDistrict().getName(),"", Message.Type.DEADTRIBUTE));
                    webSocketController.dropAllWeapon(tribute, x, y);
                    if (gameProcessService.isGameOver(gameToday)) tributesToday = new ArrayList<>();
                }
                webSocketController.getHealth(new TributeHealth(tribute.getUser().getNick(),tribute.getHealth(), tribute.getHunger(), tribute.getThirst()));
            }
        }
    }

    @Scheduled(cron = "0 */30 10-23 * * *")
    public void createHook(){
        init();
        if (gameToday!=null) {
            int x, y;
            x = (int) (Math.random() * gameToday.getArena().getArenaLength() + 1);
            y = (int) (Math.random() * gameToday.getArena().getArenaWidth() + 1);
            Map map = mapService.getCell(gameToday.getArena(),x,y);
            if (map!=null) {
                List<Hook> hooks = hookService.getHookByLocation(map.getLocation());
                if (hooks.size() != 0) {
                    Hook hook = hooks.get((int) (Math.random() * hooks.size()));
                    hookService.activateHook(gameToday,hook,x,y);
                }
            }
        }
    }

    private void init(){
        Calendar today = new GregorianCalendar();
        today.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH),0,0,0);
        gameToday = gameService.getGameByStartDate(today);
        if (gameToday!=null) {
            if (gameToday.getStatus().equals("game over")){
                gameToday = null;
                tributesToday = new ArrayList<>();
            } else {
                tributesToday = tributeService.getTributesByGame(gameToday);
            }
        } else {
            tributesToday = new ArrayList<>();
        }
    }
}
