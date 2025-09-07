package com.example.learning.dto;

import lombok.Data;

@Data
public class NotiTemplate {

    String token;
    String settingType;
    TemplateData data;
    TemplateNotification notification;
}
