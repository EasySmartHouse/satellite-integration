package net.easysmarthouse.satellite.integration.transformer;

import net.easysmarthouse.satellite.integration.service.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Service;

@Service
public class ReadValueTransformer implements ResultTransformer<String, Double> {

    @Autowired
    private SensorsService sensorsService;

    @Override
    @Transformer
    public Double transform(String address) {
        return sensorsService.readValue(address);
    }

}
