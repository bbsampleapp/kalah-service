package io.filer.kalahservice.config;

import io.filer.kalahservice.model.DataHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KalahConfig {

    @Bean
    public DataHolder createDataHolder() {
        return new DataHolder();
    }
}
