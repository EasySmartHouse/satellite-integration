package net.easysmarthouse.satellite.integration.service.impl;

import net.easysmarthouse.satellite.integration.service.MemoryService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MapMemoryService implements MemoryService {

    private Map<String, byte[]> memory = new HashMap<>();

    @Override
    public Boolean write(String address, byte[] value) {
        memory.put(address, value);
        return true;
    }

    @Override
    public byte[] read(String address) {
        return memory.get(address);
    }
}
