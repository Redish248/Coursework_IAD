import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@SpringBootApplication
@ComponentScan(basePackages = {"logging", "service", "entity", "controller", "impl", "config.security", "config.websocket", "config.logger"})
@EntityScan("entity")
@EnableJpaRepositories("repository")
@EnableScheduling
//FIXME: раскомментировать потом, for frontend
//@EnableOAuth2Client
public class Application {


    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

}
