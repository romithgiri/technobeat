package com.rohit.technbeat.userModules
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessageFromCloud:FirebaseMessagingService() {
    override fun onMessageReceived(p0: RemoteMessage?) {
        super.onMessageReceived(p0)
    }
}