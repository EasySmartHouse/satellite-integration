package net.easysmarthouse.satellite.integration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;

@Configuration
public class TransformerConfig {

    @Autowired
    private Jackson2JsonObjectMapper jackson2JsonObjectMapper;

    @Bean
    public ObjectToJsonTransformer objectToJsonTransformer() {
        return new ObjectToJsonTransformer(jackson2JsonObjectMapper);
    }

}
