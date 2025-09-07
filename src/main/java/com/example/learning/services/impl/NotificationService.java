package com.example.learning.services.impl;

import com.example.learning.dto.NotiTemplate;
import com.example.learning.services.INotificationService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Properties;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private Gson gson;


    private NotiTemplate readTemplate(String templateName) {
        try {
            ClassPathResource resource = new ClassPathResource("templates/" + templateName + ".txt");
            File file = resource.getFile();
            String template = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            return gson.fromJson(template, NotiTemplate.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void sendNotification(String fcmToken, String templateName)  {

        NotiTemplate template = readTemplate(templateName);
        if(Objects.isNull(template)) return;

        String pushContent = gson.toJson(template);
        Message message = gson.fromJson(pushContent
                .replace("\"token\":\"\",", "\"token\":\"" + fcmToken + "\","), Message.class);

        try {
            FirebaseMessaging.getInstance().send(message);
            System.out.println("Notification sent successfully.");
        } catch (Exception e) {
            System.err.println("Error sending notification: " + e.getMessage());
        }
    }
}