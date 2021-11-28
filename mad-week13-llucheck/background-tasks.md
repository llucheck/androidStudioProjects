# Background Tasks
- Need to be conscious of how the app is consuming resources
- Android has become more restrictive regarding what is happening in the Background

## Services
- A service is one of the four Android core components (Activity, Service, Broadcast Receiver, Content Provider)
- Long running background tasks that don’t need a visual component.  There is no UI with a service.
- An activity can start a service that continues to run after the activity is closed
- Three ways to start a service
	- Manually start a service using an intent
	- Schedule - use a Job Scheduler to start a JobService
	- Bind to a service
- Services are started on the main thread and should pass off task to a background thread


## Issuing Reminders
- Use an [AlarmManager](https://developer.android.com/reference/android/app/AlarmManager.html) which provides access to the system alarm services.
	- The Alarm Manager is intended for cases where you want to have your application code run at a specific time, even if your application is not currently running.


## Broadcast Receiver
- When certain things happen (e.g. headphones unplugged)  the system broadcasts notifications
- A Broadcast Receiver is a core Android component that enables applications to receive intents that are broadcast by the system or by other applications
	- Can be triggered even when the app isn’t running
- Specify which broadcasts the Broadcast Receiver is interested in by using an Intent Filter that specifies which intents should trigger your components
- Two ways to create broadcast receiver
    - Static - triggered even if the app is offline
        - Registered in the manifest
    -  Dynamic - tied to the app’s lifecycle
        - Registered and unregistered in the activity’s lifecycle methods, such as `onResume` and `onPause`
