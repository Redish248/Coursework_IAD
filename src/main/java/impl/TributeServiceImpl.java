package impl;

import entity.Game;
import entity.Status;
import entity.Tribute;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.MapRepository;
import repository.SkillRepository;
import repository.StatusRepository;
import repository.TributeRepository;
import repository.UserRepository;
import repository.UserSkillRepository;
import service.TributeService;

import java.util.List;


@Service("tributeService")
public class TributeServiceImpl implements TributeService {

    private final TributeRepository tributeRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final MapRepository mapRepository;
    private final SkillRepository skillRepository;
    private final UserSkillRepository userSkillRepository;


    @Autowired
    public TributeServiceImpl(TributeRepository tributeRepository, UserRepository userRepository, StatusRepository statusRepository, MapRepository mapRepository, SkillRepository skillRepository, UserSkillRepository userSkillRepository) {
        this.tributeRepository = tributeRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.mapRepository = mapRepository;
        this.skillRepository = skillRepository;
        this.userSkillRepository = userSkillRepository;
    }

    /**
     * It creates tribute after checking his age
     * @param tribute tribute
     * @return tribute if age is correct and null if incorrect
     */
    @Transactional
    @Override
    public Tribute createTribute(Tribute tribute) {
        tributeRepository.save(tribute);
        User user = userRepository.findUserByTributesByUser(tribute);
        Status status = statusRepository.findStatuseByName("tribute");
        user.setStatus(status);
        userRepository.save(user);
        return tribute;
    }

    @Transactional
    @Override
    public boolean deleteTribute(Tribute tribute) {
        tributeRepository.delete(tribute);
        return true;
    }

    @Transactional
    @Override
    public Tribute updateTribute(Tribute tribute) {
        return tributeRepository.save(tribute);
    }

    @Override
    public Tribute getTributeById(int tributeId) {
        return tributeRepository.findTributeByTributeId(tributeId);
    }

    @Override
    public List<Tribute> getTributesByUser(User user) {
        return tributeRepository.getTributesByUser(user);
    }

    @Override
    public List<Tribute> getTributesByGame(Game game) {
        return tributeRepository.getTributesByGame(game);
    }

    @Override
    public List<Tribute> getTributesByStatusAndGame(String status, Game game) {
        return tributeRepository.getTributesByStatusAndGame(status, game);
    }

    @Override
    public Tribute getTributeByUserAndGame(User user, Game game){
        return tributeRepository.getTributeByUserAndGame(user, game);
    }

    @Override
    public List<Tribute> getTributeInArea(Game game, int x, int y, int radius) {
        return tributeRepository.getTributesByGameAndLocationXBetweenAndLocationYIsBetween(game, x-radius,x+radius,y-radius,y+radius);
    }

    @Override
    public void moveTribute(Tribute tribute, int newX, int newY) {
        tribute.setLocationX(newX);
        tribute.setLocationY(newY);

        tributeRepository.save(tribute);
        /*int koef = 2;
        Skill necessarySkill = null;
        String curLocation = mapRepository.findMapByArenaAndXCoordinateAndYCoordinate(tribute.getGame().getArena(), newX, newY).getLocation().getName();
        switch (curLocation){
            case "Горы": necessarySkill = skillRepository.findSkillByName("Скалолазание");
                break;
            case "Водная": necessarySkill = skillRepository.findSkillByName("Плавание");
                break;
        }
        if (necessarySkill!=null){
            if (!tribute.getUser().getSkills().contains(necessarySkill)){
                tribute.setHealth(tribute.getHealth() - koef);
            } else {
                tribute.setHealth(tribute.getHealth()-koef*(100-userSkillRepository.findUserSkillByUserAndSkill(tribute.getUser(), necessarySkill).getLevelOfSkill()));
            }
        }
        if (tribute.getHealth()<=0){
            tribute.setStatus("dead");
        }*/
    }

    @Override
    public int getDamage(Tribute tribute, int damage) {
        int health = tribute.getHealth()-damage<0 ? 0 : tribute.getHealth()-damage;
        User user = tribute.getUser();
        Status status = statusRepository.findStatuseByName("Наблюдатель");
        damage = tribute.getHealth()-health;
        tribute.setHealth(health);
        if (tribute.getHealth()<=0) {
            tribute.setStatus("dead");
            tribute.setLocationX(-1);
            tribute.setLocationY(-1);
            user.setStatus(status);
            userRepository.save(user);
        }
        tributeRepository.save(tribute);
        return damage;
    }


    @Override
    public void getHunger(Tribute tribute, int hunger) {
        tribute.setHunger(tribute.getHunger()-hunger<0 ? 0 : tribute.getHunger()-hunger);
        tributeRepository.save(tribute);
    }


    @Override
    public void getThirst(Tribute tribute, int thirst) {
        tribute.setThirst(tribute.getThirst()-thirst<0 ? 0 : tribute.getThirst()-thirst);
        tributeRepository.save(tribute);
    }
}
