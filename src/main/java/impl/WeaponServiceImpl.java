package impl;

import entity.Skill;
import entity.Tribute;
import entity.Weapon;
import entity.WeaponsInGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.WeaponRepository;
import repository.WeaponsInGameRepository;
import service.WeaponService;

import java.util.ArrayList;
import java.util.List;

@Service("weaponService")
public class WeaponServiceImpl implements WeaponService {

    private final WeaponRepository weaponRepository;
    private final WeaponsInGameRepository weaponsInGameRepository;

    @Autowired
    public WeaponServiceImpl(WeaponRepository weaponRepository, WeaponsInGameRepository weaponsInGameRepository) {
        this.weaponRepository = weaponRepository;
        this.weaponsInGameRepository = weaponsInGameRepository;
    }

    @Transactional
    @Override
    public List<Weapon> getTributeWeapons(Tribute tribute) {
        List<Weapon> tributeWeapons = new ArrayList<>();
        List<WeaponsInGame> gameWeapon = weaponsInGameRepository.getWeaponsInGamesByTribute(tribute);
        List<Weapon> allWeapons = (List<Weapon>) weaponRepository.findAll();
        for (Weapon weapons : allWeapons) {
            for (WeaponsInGame weapon : gameWeapon) {
                if (weapons == weapon.getWeapon()) {
                    tributeWeapons.add(weapons);
                }
            }
        }
        return tributeWeapons;
    }

    @Transactional
    @Override
    public Weapon createWeapon(Weapon weapon) {
        return weaponRepository.save(weapon);
    }

    @Transactional
    @Override
    public boolean deleteWeapon(Weapon weapon) {
        weaponRepository.delete(weapon);
        return true;
    }

    @Transactional
    @Override
    public Weapon updateWeapon(Weapon weapon) {
        return weaponRepository.save(weapon);
    }

    @Override
    public Weapon getWeaponById(int id) {
        return weaponRepository.findWeaponByWeaponId(id);
    }

    @Override
    public Weapon getWeaponBySkill(Skill skill) {
        return weaponRepository.getWeaponBySkill(skill);
    }

    @Override
    public List<Weapon> getWeaponsByOwners(Tribute tribute) {
        return weaponRepository.getWeaponByOwners(tribute);
    }

    @Override
    public Weapon getWeaponByName(String name) {
        return weaponRepository.findWeaponByName(name);
    }

    @Override
    public int getProtectionOftribute(Tribute tribute) {
        List<Weapon> weapons = weaponRepository.getWeaponByOwners(tribute);
        int coef = 0;
        for (Weapon weapon: weapons){
            if (weapon.getTypeOfWeapon().equals("Защитное")){
                coef += weapon.getDamage();
            }
        }
        if (coef>100) coef = 100;
        return coef;
    }
}
