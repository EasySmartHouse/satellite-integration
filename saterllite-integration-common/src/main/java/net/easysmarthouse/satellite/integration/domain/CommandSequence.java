package net.easysmarthouse.satellite.integration.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CommandSequence implements Serializable {

    private Integer id;
    private Date timestamp;
    private List<Command> sequence = new LinkedList<>();

    public CommandSequence() {
    }

    public CommandSequence(Integer id) {
        this.id = id;
        this.timestamp = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void add(Command command) {
        this.sequence.add(command);
    }

    public List<Command> getSequence() {
        return sequence;
    }

    public void setSequence(List<Command> sequence) {
        this.sequence = sequence;
    }
}
