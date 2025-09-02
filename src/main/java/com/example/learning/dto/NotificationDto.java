package com.example.learning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto implements Serializable {
    private String fcm_device_token;
    private String title;
    private String body;
}
