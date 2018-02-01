package com.foodorderingapp.service;

import com.foodorderingapp.model.User;

/**
 * Created by TOPSHI KREATS on 1/30/2018.
 */
public interface NotificationService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);

}
