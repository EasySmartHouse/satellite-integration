package net.easysmarthouse.satellite.integration.service.impl;

import net.easysmarthouse.satellite.integration.service.SwitchService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemorySwitchService implements SwitchService{

    private Map<String, Boolean> switches = new HashMap<>();

    @Override
    public Boolean changeState(String address, Boolean state) {
        switches.put(address, state);
        return true;
    }

    @Override
    public Boolean getState(String address) {
        return switches.get(address);
    }
}
