package com.example.learning.services;

public interface INotificationService {
    void sendNotification(String deviceToken,  String templateName);
}
