import config.database.DatabaseConfig;
import entity.*;
import impl.WeaponsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.*;
import service.WeaponsService;

import java.sql.Date;
import java.util.List;

public class Main {


    public static void main(String[] args){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConfig.class);


        LocationsEntity locationsEntity = new LocationsEntity("forest", "forest.jpg");
        LocationsRepository locationsRepository = (LocationsRepository)ctx.getBean("locationsRepository");
        locationsRepository.save(locationsEntity);

        ArenasEntity arenasEntity = new ArenasEntity();
        arenasEntity.setArenaLength((short) 123);
        arenasEntity.setArenaWidth((short)12);
        arenasEntity.setMainLocation(locationsEntity);

        ArenasRepository arenasRepository = (ArenasRepository) ctx.getBean("arenasRepository");

        arenasRepository.save(arenasEntity);

////
        DistrictsRepository districtsRepository = (DistrictsRepository) ctx.getBean("districtsRepository");
        SkillsRepository skillsRepository = (SkillsRepository) ctx.getBean("skillsRepository");

        DistrictsEntity districtsEntity = new DistrictsEntity();
        districtsEntity.setName("Дистрикт 55");
        districtsEntity.setTypeofactivity("Роскошь");
///
        SkillsEntity skillsEntity = new SkillsEntity();
        skillsEntity.setName("Изготовление украшений");
        skillsEntity.setTypeofskill("Другое");
        skillsEntity.setDescription("kek lol");

        skillsRepository.save(skillsEntity);
        districtsEntity.setSkillsBySkillid(skillsEntity);
        districtsRepository.save(districtsEntity);

        SkillsEntity skillsEntity1 = skillsRepository.findSkillsEntityByDistrictsBySkillid(districtsEntity);
        System.out.println(skillsEntity1.getDescription());
/////////////////////////

        UserloginEntity userlogin = new UserloginEntity();
        userlogin.setNick("redish248");
        userlogin.setPassword("12345");
        UserloginRepository userloginRepository = (UserloginRepository)ctx.getBean("userloginRepository");
        userloginRepository.save(userlogin);

        UsersEntity user = new UsersEntity();
        user.setName("Ira");
        user.setSurname("Redkina");
        user.setPicturePath("/");
        user.setUserlogin(userlogin);
        UserRepository userRepository = (UserRepository)ctx.getBean("userRepository");
        userRepository.save(user);

        GamesEntity game = new GamesEntity();
        GamesRepository gamesRepository = (GamesRepository) ctx.getBean("gamesRepository");
        game.setArenasByArena(arenasEntity);
        game.setDuration(15);
        game.setTypeofgame(true);
        game.setStartdate(new Date(1));
        gamesRepository.save(game);

        TributesEntity tribute = new TributesEntity();
        TributesRepository tributesRepository = (TributesRepository) ctx.getBean("tributesRepository");
        tribute.setUsersByUserid(user);
        tribute.setGamesByGameid(game);
        tributesRepository.save(tribute);


        WeaponsEntity weapon1 = new WeaponsEntity();
        weapon1.setName("меч");
        weapon1.setPicturePath("/");
        weapon1.setTypeofweapon("Холодное оружие");
        WeaponsEntity weapon2 = new WeaponsEntity();
        weapon2.setName("лук");
        weapon2.setPicturePath("/");
        weapon2.setTypeofweapon("что-то там");
        WeaponsRepository weaponsRepository = (WeaponsRepository)ctx.getBean("weaponsRepository");
        weaponsRepository.save(weapon1);
        weaponsRepository.save(weapon2);


        WeaponsingameEntity weaponsingameEntity1 = new WeaponsingameEntity();
        weaponsingameEntity1.setTributesByTributeid(tribute);
        weaponsingameEntity1.setWeaponsByWeaponid(weapon1);
        WeaponsingameEntity weaponsingameEntity2 = new WeaponsingameEntity();
        weaponsingameEntity2.setTributesByTributeid(tribute);
        weaponsingameEntity2.setWeaponsByWeaponid(weapon2);
        WeaponsingameRepository weaponsingameRepository = (WeaponsingameRepository)ctx.getBean("weaponsingameRepository");
        weaponsingameRepository.save(weaponsingameEntity1);
        weaponsingameRepository.save(weaponsingameEntity2);

        UserskillsEntity userskillsEntity = new UserskillsEntity();
        userskillsEntity.setSkillsBySkillid(skillsEntity);
        userskillsEntity.setUsersByUserid(user);
        userskillsEntity.setLevelofskill((short)20);
        UserskillsRepository userskillsRepository = (UserskillsRepository)ctx.getBean("userskillsRepository");
        userskillsRepository.save(userskillsEntity);

        ShopEntity product = new ShopEntity("Веревка", (short) 120, "Инструменты", "Прочная длинная веревка", (short) 0, "rope.jpg");
        ShopRepository shopRepository = (ShopRepository)ctx.getBean("shopRepository");
        shopRepository.save(product);

        ProductsandlocationEntity productsandlocationEntity = new ProductsandlocationEntity();
        productsandlocationEntity.setLocation(locationsEntity);
        productsandlocationEntity.setShopByProductid(product);
        ProductsandlocationRepository productsandlocationRepository = (ProductsandlocationRepository)ctx.getBean("productsandlocationRepository");
        productsandlocationRepository.save(productsandlocationEntity);

        /*WeaponsServiceImpl weaponsService = new WeaponsServiceImpl(weaponsRepository, weaponsingameRepository);
        List<WeaponsEntity> list = weaponsService.getTributeWeapons(tribute);
        for (WeaponsEntity weaponsEntity : list) {
            System.out.println(weaponsEntity.getName());
        }
        */

    }
}