package com.remoteclassnotes;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

public class Utils {
    public static void logout() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            FirebaseAuth.getInstance().signOut();
        }
    }

    public static void unSubscribeFromNotification(String topic) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
    }

    public static void subscribeToTopic(String topic) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic);
    }
}
