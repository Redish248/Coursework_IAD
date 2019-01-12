package controller;

import entity.User;
import entity.UserSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import service.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

@RestController
@RequestMapping("/hungergames")
@EnableAutoConfiguration
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    StatusService statusService;

    @Autowired
    DistrictService districtService;

    @Autowired
    SkillService skillService;

    @Autowired
    UserSkillService userSkillService;

    @PostMapping( "/signup")
    public @ResponseBody ResponseEntity registerUser(String username, String password, boolean sex, String name, String surname, int height, int weight, String birthday, byte[] picture) {
        int defaultCash = 1000;//TODO: надо из прайс-листа бы доставать
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(birthday));
        User user = new User(username, BCrypt.hashpw(password, BCrypt.gensalt()), surname, name, height, weight, sex, districtService.getDistrictById((int)(Math.random()*12+1)), calendar, picture, statusService.getStatuseById(1));
        UserSkill userSkill = new UserSkill(user, skillService.getSkillById(user.getDistrict().getDistrictId()), 100);
        if ((username.equals("")) || (password.equals(""))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect username or password");
        }
        User someUser = userService.getUserByNick(username);
        if (someUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }
        userService.createUser(user);
        userSkillService.createUserSkill(userSkill);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


}