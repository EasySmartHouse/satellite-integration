package net.easysmarthouse.satellite.integration.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class CommandResult implements Serializable {

    private ResponseCode code;
    private byte[] data;
    private Date timestamp;

    public CommandResult() {
    }

    public CommandResult(ResponseCode code, byte[] data) {
        this.code = code;
        this.data = data;
        this.timestamp = new Date();
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CommandResult{" +
                "code=" + code +
                ", data=" + Arrays.toString(data) +
                ", timestamp=" + timestamp +
                '}';
    }
}
