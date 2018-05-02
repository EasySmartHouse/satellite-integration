package net.easysmarthouse.satellite.integration.service;

public interface WatchdogService {

    public Boolean sleep(String address);

    public Boolean wakeUp(String address);

    public Boolean isSleeping(String address);

}
