package controller;

import entity.Arena;
import entity.Game;
import entity.Hook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.ArenaService;
import service.GameProcessService;
import service.GameService;
import service.HookService;
import service.LocationService;
import service.UserService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/hungergames")
@EnableAutoConfiguration
public class StewardController {

    private final GameService gameService;
    private final ArenaService arenaService;
    private final LocationService locationService;
    private final UserService userService;
    private final GameProcessService gameProcessService;
    private final HookService hookService;
    private final SessionRegistry sessionRegistry;

    @Autowired
    public StewardController(GameService gameService, ArenaService arenaService, LocationService locationService, UserService userService, GameProcessService gameProcessService, HookService hookService, SessionRegistry sessionRegistry) {
        this.gameService = gameService;
        this.arenaService = arenaService;
        this.locationService = locationService;
        this.userService = userService;
        this.gameProcessService = gameProcessService;
        this.hookService = hookService;
        this.sessionRegistry = sessionRegistry;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping( "/create_game")
    public @ResponseBody ResponseEntity createGame(boolean typeOfGame, int length, int width, String locationName, long startDate) {
        Calendar date = Calendar.getInstance();
        date.setTime(new Date(startDate));
        if (length>=7 && width>=7) {
            if (gameService.getGameByStartDate(date) == null) {
                entity.User user = userService.getUserByNick(SecurityContextHolder.getContext().getAuthentication().getName());
                Arena arena = new Arena(length, width, locationService.findLocationByName(locationName));
                arenaService.createArena(arena);
                int numberOfTributes;
                if (typeOfGame) {
                    numberOfTributes = 24;
                } else {
                    numberOfTributes = 48;
                }
                Game game = new Game(typeOfGame, user, arena, numberOfTributes, date);
                gameService.createGame(game);
                //return selectTributes(game.getGameId());
                return ResponseEntity.status(HttpStatus.OK).body(game);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body("Этот день для проведения игр уже занят.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Размер арены должен быть не меньше 7*7.");
        }

    }

    @Secured("ROLE_ADMIN")
    @PostMapping( "/add_role")
    public @ResponseBody ResponseEntity addRoleToUser(String nick, String role) {
        entity.User user = userService.getUserByNick(nick);
        userService.addRole(user, role);
        return ResponseEntity.status(HttpStatus.OK).body("Роль добавлена");
    }

    @Secured("ROLE_ADMIN")
    @PostMapping( "/del_role")
    public @ResponseBody ResponseEntity removeRoleFromUser(String nick, String role) {
        entity.User user = userService.getUserByNick(nick);
        userService.removeRole(user, role);
        return ResponseEntity.status(HttpStatus.OK).body("Роль удалена");
    }

    @Secured("ROLE_ADMIN")
    @PostMapping( "/get_hooks")
    public @ResponseBody ResponseEntity getHooks() {
        List<Hook> hooks = hookService.getAllHooks();
        List<String> hooksName = new ArrayList<>();
        for (Hook hook: hooks){
            hooksName.add(hook.getName());
        }
        return ResponseEntity.status(HttpStatus.OK).body(hooksName);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping( "/tributeSelection")
    public @ResponseBody ResponseEntity selectTributes(int gameID) {
        final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        Game game = gameService.getGameById(gameID);
        List<entity.User> usersOnline = new ArrayList<>();
        List<entity.User> tributes;

        for(final Object principal : allPrincipals) {
            if(principal instanceof org.springframework.security.core.userdetails.User) {
                usersOnline.add(userService.getUserByNick(((org.springframework.security.core.userdetails.User)principal).getUsername()));
            }
        }

        tributes = gameProcessService.selection(usersOnline, game);
        if (tributes.size()==game.getNumberOfTributes()){
            return ResponseEntity.status(HttpStatus.OK).body(tributes);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Недостаточное число трибутов!");
        }
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/createHook")
    public @ResponseBody ResponseEntity createHook(int x, int y, String hookName){
        Game game = gameService.getGameByStartDate(Calendar.getInstance());
        Hook hook = hookService.getHookByName(hookName);
        if (hookService.activateHook(game, hook, x, y)) {
            return ResponseEntity.status(HttpStatus.OK).body("Ловушка установлена");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Ловушка не подходит для текущей локации");
        }
    }
}
