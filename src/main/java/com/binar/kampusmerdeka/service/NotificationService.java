package com.binar.kampusmerdeka.service;

import com.binar.kampusmerdeka.dto.NotificationGet;
import com.binar.kampusmerdeka.dto.NotificationRequest;
import com.binar.kampusmerdeka.dto.NotificationResponse;

import java.util.concurrent.ExecutionException;

public interface NotificationService {

    NotificationResponse createNotification(NotificationRequest notificationRequest) throws ExecutionException, InterruptedException;

    NotificationGet getNotificationDetails(String title) throws ExecutionException, InterruptedException;

    String deleteNotification(String title) throws ExecutionException, InterruptedException;
}
