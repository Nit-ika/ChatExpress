package com.mycompany.tcs.dao;

import com.mycompany.tcs.model.Employees;
import com.mycompany.tcs.model.Notifications;
import java.util.List;

public interface NotificationDAO {
    void addNotifications(Notifications not);
    void editNotifications(Notifications not);
    void deleteNotifications(int notId);
    int getNotificationSenderId(int notId);
    Employees getNotificationSender(int notId);
    int getNotificationReceiverId(int notId);
    Employees getNotificationReceiver(int notId);
    Notifications findNotificationbyId(int notId);    
    List<Notifications> getAllNotifications();
}
