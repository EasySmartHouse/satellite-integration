package net.easysmarthouse.satellite.integration.domain;

import java.io.Serializable;
import java.util.Date;

public class Command implements Serializable {

    private String address;
    private CommandType commandType;
    private byte[] data;
    private Date timestamp;

    public Command() {
    }

    public Command(String address, CommandType commandType) {
        this.address = address;
        this.commandType = commandType;
        this.timestamp = new Date();
    }

    public Command(String address, CommandType commandType, byte[] data) {
        this.address = address;
        this.commandType = commandType;
        this.data = data;
        this.timestamp = new Date();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
