package com.binar.kampusmerdeka.service.impl;

import com.binar.kampusmerdeka.dto.NotificationGet;
import com.binar.kampusmerdeka.dto.NotificationRequest;
import com.binar.kampusmerdeka.dto.NotificationResponse;
import com.binar.kampusmerdeka.service.NotificationService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.ExecutionException;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final String COLLECTION_NAME = "notifications";

    @Override
    public NotificationResponse createNotification(NotificationRequest notificationRequest) throws ExecutionException, InterruptedException {
        notificationRequest.setCreatedAt(Instant.now());
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(notificationRequest.getTitle()).set(notificationRequest);

        if(collectionApiFuture.isCancelled())
            return null;
        else
            return NotificationResponse.builder()
                    .title(notificationRequest.getTitle())
                    .content(notificationRequest.getContent())
                    .statusRead(notificationRequest.getStatusRead())
                    .build();
    }

    @Override
    public NotificationGet getNotificationDetails(String title) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference= dbFirestore.collection(COLLECTION_NAME).document(title);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();

        if(documentSnapshot.exists())
        {
            return documentSnapshot.toObject(NotificationGet.class);
        }
        else
            return null;
    }

    @Override
    public String deleteNotification(String title) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection(COLLECTION_NAME).document(title).delete();

        return "Document with notification ID : " + title + " has been deleted successfully";
    }
}
