package net.easysmarthouse.satellite.integration.service;

public interface SwitchService {

    public Boolean changeState(String address, Boolean state);

    public Boolean getState(String address);

}
