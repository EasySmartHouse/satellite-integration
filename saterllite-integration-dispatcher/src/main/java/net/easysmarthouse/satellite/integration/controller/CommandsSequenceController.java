package net.easysmarthouse.satellite.integration.controller;

import net.easysmarthouse.satellite.integration.domain.CommandSequence;
import net.easysmarthouse.satellite.integration.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/commandsSequence")
public class CommandsSequenceController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<?> send(@RequestBody CommandSequence sequence) {
        if (sequence == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        commandGateway.sendCommandSequence(sequence);
        return new ResponseEntity<>(sequence, HttpStatus.CREATED);
    }

}
