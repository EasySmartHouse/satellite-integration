package net.easysmarthouse.satellite.integration.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class DeliveryPacket implements Serializable {

    private List<CommandResult> commandResult;

    public DeliveryPacket() {
    }

    public DeliveryPacket(List<CommandResult> commandResult) {
        this.commandResult = commandResult;
    }

    public List<CommandResult> getCommandResult() {
        return commandResult;
    }

    public void setCommandResult(List<CommandResult> commandResult) {
        this.commandResult = commandResult;
    }

    @Override
    public String toString() {
        return "DeliveryPacket{" +
                "commandResult=" + Arrays.toString(commandResult.toArray()) +
                '}';
    }
}
