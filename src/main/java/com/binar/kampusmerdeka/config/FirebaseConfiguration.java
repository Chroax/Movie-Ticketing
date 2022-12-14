package com.binar.kampusmerdeka.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FirebaseConfiguration {
    @Value("${myapp.firebase.file}")
    private String fbFile = "";

    @Primary
    @Bean
    public void initialization(){

        try {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(
                            new ClassPathResource(fbFile).getInputStream()
                    ))
                    .build();

            if(FirebaseApp.getApps().isEmpty())
                FirebaseApp.initializeApp(options);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
