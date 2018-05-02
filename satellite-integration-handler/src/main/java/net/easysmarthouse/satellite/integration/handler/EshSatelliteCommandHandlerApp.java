package net.easysmarthouse.satellite.integration.handler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan
public class EshSatelliteCommandHandlerApp {

    public static void main(String[] args) {
        SpringApplication.run(EshSatelliteCommandHandlerApp.class, args);
    }


}
