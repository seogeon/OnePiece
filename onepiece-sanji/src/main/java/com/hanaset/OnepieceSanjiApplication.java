package com.hanaset;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class OnepieceSanjiApplication {

    private final Environment environment;

    public OnepieceSanjiApplication(Environment environment) {
        this.environment = environment;
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(OnepieceSanjiApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReadyEvent() {
        log.info("applicationReady profiles:{}", Arrays.toString(environment.getActiveProfiles()));
    }

}
