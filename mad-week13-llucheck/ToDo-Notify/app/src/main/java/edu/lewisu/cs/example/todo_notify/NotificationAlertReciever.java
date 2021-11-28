package edu.lewisu.cs.example.todo_notify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationAlertReciever extends BroadcastReceiver {
    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";
    public static final String ACTION_REVIEW_REMINDER = "review-reminder";
    @Override
    public void onReceive(Context context, Intent intent){
        String action = intent.getAction();
        Log.d(NotificationAlertReciever.class.getSimpleName(), "action =" + action);

        if(action.equals(ACTION_REVIEW_REMINDER)){
            NotificationUtils.reminderUser(context);
        }else if(action.equals(ACTION_DISMISS_NOTIFICATION)){
            NotificationUtils.clearNotification(context);
        }
    }

}
