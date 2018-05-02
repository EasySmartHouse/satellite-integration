package net.easysmarthouse.satellite.integration.transformer;

import net.easysmarthouse.satellite.integration.service.SwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Service;

@Service
public class SwitchOffTransformer implements ResultTransformer<String, Boolean> {

    @Autowired
    private SwitchService switchService;

    @Override
    @Transformer
    public Boolean transform(String address) {
        return switchService.changeState(address, false);
    }
}
