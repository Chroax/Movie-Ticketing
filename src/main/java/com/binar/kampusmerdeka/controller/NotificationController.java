package com.binar.kampusmerdeka.controller;

import com.binar.kampusmerdeka.dto.MessageModel;
import com.binar.kampusmerdeka.dto.NotificationGet;
import com.binar.kampusmerdeka.dto.NotificationRequest;
import com.binar.kampusmerdeka.dto.NotificationResponse;
import com.binar.kampusmerdeka.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/add")
    public ResponseEntity<MessageModel> createNotification(@RequestBody NotificationRequest notificationRequest) throws ExecutionException, InterruptedException {
        MessageModel messageModel = new MessageModel();
        NotificationResponse notificationResponse = notificationService.createNotification(notificationRequest);

        if(notificationResponse == null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage("Failed create new notification");
            log.error("Failed create new notification");
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Create new notification");
            messageModel.setData(notificationResponse);
            log.info("Success create new notification");
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<MessageModel> getNotification(@PathVariable String title) throws ExecutionException, InterruptedException {

        MessageModel messageModel = new MessageModel();
        NotificationGet notificationResponse = notificationService.getNotificationDetails(title);

        if(notificationResponse == null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage("Failed get notification with title " + title);
            log.error("Failed get notification with title {}", title);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Success get notification with title : " + title);
            messageModel.setData(notificationResponse);
            log.info("Success get notification with title {}", title);
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @DeleteMapping("/delete/{title}")
    public ResponseEntity<MessageModel> createNotification(@PathVariable String title) throws ExecutionException, InterruptedException {
        MessageModel messageModel = new MessageModel();
        messageModel.setStatus(HttpStatus.OK.value());
        messageModel.setMessage(notificationService.deleteNotification(title));
        return ResponseEntity.ok().body(messageModel);
    }
}
