package webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MainRunner extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MainRunner.class, args);
    }
}
