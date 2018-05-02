package net.easysmarthouse.satellite.integration.transformer;

import net.easysmarthouse.satellite.integration.service.WatchdogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Service;

@Service
public class WakeUpTransformer implements ResultTransformer<String, Boolean> {

    @Autowired
    private WatchdogService watchdogService;

    @Override
    @Transformer
    public Boolean transform(String address) {
        return watchdogService.wakeUp(address);
    }
}
