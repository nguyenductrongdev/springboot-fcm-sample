package com.example.learning.controllers;

import com.example.learning.services.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/push")
@RequiredArgsConstructor
public class NotificationController {

    @Autowired
    private INotificationService notificationService;

    @PostMapping
    private ResponseEntity<?> push(
            @RequestParam String fcmToken,
            @RequestParam String templateName) {
        notificationService.sendNotification(fcmToken, templateName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
