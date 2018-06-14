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

    public static void unSubscribeFromNotification() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(Constants.NOTIFICATION_TOPIC);
    }
}
