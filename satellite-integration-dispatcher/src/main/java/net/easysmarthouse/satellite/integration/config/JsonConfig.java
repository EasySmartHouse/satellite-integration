package net.easysmarthouse.satellite.integration.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;

@Configuration
public class JsonConfig {

    @Bean
    public Jackson2JsonObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return new Jackson2JsonObjectMapper(mapper);
    }

}
