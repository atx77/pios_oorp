package hr.tvz.diplomski.pios_oorp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PiosOorpApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PiosOorpApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(PiosOorpApplication.class, args);
    }
}
