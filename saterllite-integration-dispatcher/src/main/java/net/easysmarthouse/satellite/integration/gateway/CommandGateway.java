package net.easysmarthouse.satellite.integration.gateway;

import net.easysmarthouse.satellite.integration.domain.CommandSequence;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface CommandGateway {

    @Gateway(requestChannel = "sendCommandSequenceChannel")
    public void sendCommandSequence(CommandSequence sequence);

}
