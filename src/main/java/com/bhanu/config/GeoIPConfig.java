package com.bhanu.config;

import com.maxmind.geoip2.DatabaseReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class GeoIPConfig {

    @Value("${geoip.database.path}")
    private String databasePath;

    @Bean
    public DatabaseReader databaseReader() throws Exception {

        File file = new File(databasePath);

        if(!file.exists()){
            throw new RuntimeException("GeoIP Database not found at: " + databasePath);
        }

        return new DatabaseReader.Builder(file).build();
    }
}
