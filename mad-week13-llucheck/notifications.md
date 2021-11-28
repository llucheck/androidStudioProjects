# Notifications
Notifications are messages that appear outside of the app's UI to provide users with information and reminders.

## Creating a notification
- Use the support library to provide features available only on newer versions of Android while still providing compatibility back to Android 4.0 (API level 14).
- To set the content of the notification use the [NotificationCompat.Builder](https://developer.android.com/reference/androidx/core/app/NotificationCompat) class
- Some items to include in the builder include
	- A small icon, set by `setSmallIcon()`. This is the only user-visible content that's required.
	- A title, set by `setContentTitle()`.
	- The body text, set by `setContentText()`.
	- The notification priority, set by `setPriority()`.
	- The response to a tap of the notification, usually to open an activity in your app that corresponds to the notification. To do so, you must specify a content intent defined with a `PendingIntent` object and pass it to `setContentIntent()`.
	- One to three action buttons set by passing a PendingIntent to the `addAction()` method


## Reference
- [Notifications | Android Developers](https://developer.android.com/guide/topics/ui/notifiers/notifications.html)
