package com.disney.config;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ConfigServicio {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
