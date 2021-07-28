package com.example.springboot.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class Notifier {

    private NotificationManager notificationManagerImpl;

    public void notify1() {
        notificationManagerImpl.sendNotification();
    }

    @Autowired
    public Notifier setNotificationManagerImpl(NotificationManager notificationManagerImpl) {
        this.notificationManagerImpl = notificationManagerImpl;
        return this;
    }
}
