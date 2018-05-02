package net.easysmarthouse.satellite.integration.service.impl;

import net.easysmarthouse.satellite.integration.service.SensorsService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomSensorsValueService implements SensorsService{

    @Override
    public Double readValue(String address) {
        return ThreadLocalRandom.current().nextDouble(10.0,55.0);
    }
}
