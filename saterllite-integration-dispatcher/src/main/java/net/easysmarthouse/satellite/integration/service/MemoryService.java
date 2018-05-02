package net.easysmarthouse.satellite.integration.service;

public interface MemoryService {

    public Boolean write(String address, byte[] value);

    public byte[] read(String address);

}
