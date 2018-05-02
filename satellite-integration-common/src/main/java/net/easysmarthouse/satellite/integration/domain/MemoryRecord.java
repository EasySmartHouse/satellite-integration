package net.easysmarthouse.satellite.integration.domain;

import java.io.Serializable;

public class MemoryRecord implements Serializable {

    private String address;
    private byte[] data;

    public MemoryRecord(String address, byte[] data) {
        this.address = address;
        this.data = data;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
