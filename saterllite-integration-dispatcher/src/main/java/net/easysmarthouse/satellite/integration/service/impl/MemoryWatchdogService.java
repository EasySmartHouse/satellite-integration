package net.easysmarthouse.satellite.integration.service.impl;

import net.easysmarthouse.satellite.integration.service.WatchdogService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemoryWatchdogService implements WatchdogService{

    private Map<String, Boolean> memoryWatchdog = new HashMap<>();

    @Override
    public Boolean sleep(String address) {
        memoryWatchdog.put(address, true);
        return true;
    }

    @Override
    public Boolean wakeUp(String address) {
        memoryWatchdog.put(address, false);
        return null;
    }

    @Override
    public Boolean isSleeping(String address) {
        return memoryWatchdog.get(address);
    }
}
