/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.configs;

import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import lombok.AllArgsConstructor;

/**
 *
 * @author ADMIN
 */
@Configuration
@AllArgsConstructor
public class FireBaseConfiguration {

    @PostConstruct
    public void initialize() throws IOException {

        ClassPathResource resource = new ClassPathResource("serviceAccountKey.json");
        FileInputStream serviceAccount = new FileInputStream(resource.getFile());

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://alpine-effort-396416-default-rtdb.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

    }

    @Bean
    public Firestore firestore() {
        return FirestoreClient.getFirestore();
    }

}
