package edu.lewisu.cs.example.todo_notify;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class NotificationUtils {
    private static final int NOTIFICATION_REMINDER_ID = 123;
    private static final String REMINDER_NOTIFICATION_CHANNEL = "reminder_notification_channel";
    private static final int TODO_REMINDER_INTENT_ID = 5000;
    private static final int ACTION_IGNORE_INTENT_ID = 18;

    public static void reminderUser(Context context){
        Intent startActivityIntent = new Intent(context, MainActivity.class);
        PendingIntent startActivityPendingIntent = PendingIntent.getActivity(context, TODO_REMINDER_INTENT_ID, startActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent ignoreReminderIntent = new Intent(context, NotificationAlertReciever.class);
        ignoreReminderIntent.setAction(NotificationAlertReciever.ACTION_DISMISS_NOTIFICATION);
        PendingIntent ignoreReminderPendingIntent = PendingIntent.getBroadcast(context, ACTION_IGNORE_INTENT_ID, ignoreReminderIntent, 0);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(REMINDER_NOTIFICATION_CHANNEL, context.getString(R.string.channel_name), NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, REMINDER_NOTIFICATION_CHANNEL)
                .setContentIntent(startActivityPendingIntent)
                .addAction(R.drawable.ic_cancel_black_24dp, "Not now", ignoreReminderPendingIntent)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimaryDark)).setSmallIcon(R.drawable.ic_assignment_black_24dp)
                .setContentTitle(context.getString(R.string.reminder_title)).setContentText(context.getString(R.string.reminder_text))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(context.getString(R.string.reminder_text)))
                .setDefaults(Notification.DEFAULT_VIBRATE).setAutoCancel(true);

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        notificationManager.notify(NOTIFICATION_REMINDER_ID, notificationBuilder.build());

    }
    public static void clearNotification(Context context){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }
}
