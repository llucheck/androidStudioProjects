# First Application - Quiz App

## Creating an Android Project
- Select Start a new Android Studio Project
- Select Empty Activity
- Configure your new project
  - Application Name:  `QuizApp`
  - Company Domain: `yourname.cs.lewisu.edu`
  - Location: The folder containing your GitHub repository/QuizApp
  - Language: Java
  - Minimum API 21 (Lollipop)
    - Targets approximately 80% of devices
- Press Finish

## Navigating Android Studio
- Project Organization
  - manifest
  - source code
  - Resources

## Manifest
- Every application must have a manifest file (AndroidManifest.xml)
- Some features
  - Application identifier
  - Declares components
    - Launch activity
  - Declares permissions
  - Application icon

## Resources
- Contains several sub-directories for app resources.
- Some resources
    - drawable
    - layout
    - values/strings


## Gradle Scripts
- Android Studio uses Gradle to automate and manage the build process
- Two scripts
  - Project
  - Module (app) - contains application dependencies

## Layout Out User Interface
- Widgets are used to build a user interface
- Constraint layout positions widgets relative to the screen boundaries and one another
- LinearLayout stacks widgets horizontally or vertically

## Widget Attributes
- `android:layout_width` and `android:layout_height`
  - Two options `match_parent` or `wrap_content`
- `android:orientation`
- `android:text`

## String Resources
- You should always externalize application resources such strings from your code, so that you can maintain them independently.
  - Allows you to provide alternate resources
- Localization
- Performance benefits
- **Add String resources for**
  - **question text**
  - **true**
  - **false**

## Java classes
- Every Android application contains a class that extends `Activity`
  - Single thing a user can do

## onCreate method
- UI is set using the `setContentView` method
- Requires a resource ID

## Resources
- A resources is a piece of your application that is not code
- Access a resource by using its ID
- **Add IDs to buttons**

## Referring to UI Components
- Declare Java variables
- Retrieve a reference using the `findViewById` method
- **Declare and get references to both buttons**

## Auto Imports
- Select Android Studio-Preferences
- Scroll down to Editor-Auto Import


## Listeners
- Android applications are event driven
- Listeners implement a listener interface

## Toast Notifications
http://developer.android.com/guide/topics/ui/notifiers/toasts.html
- A message that pops up on the surface of the window.
- The notification automatically fades in and out
- Use `Toast` method `makeText` to create a toast
  - Context:  where it should be displayed
  - Text:  the text to be displayed
  - Duration:  how long it should be displayed
  - Example:

```java
 Toast t = Toast.makeText(context, "Beep Bop",Toast.LENGTH_SHORT);
```
- Use the `show()` method to display a Toast
  - Example:

```java
 Toast t = Toast.makeText(context, "Beep Bop",Toast.LENGTH_SHORT); 
  t.show();
```

- You can chain your methods to avoid creating a reference to a toast

```java
 Toast.makeText(context,   "Beep Bop", Toast.LENGTH_SHORT).show(); 
```

## Implement Listeners
- Add String resources for button labels and toasts
- Add private inner classes that implement `View.OnClickListener`
- In the `onClickMethod` Show a toast when a button is pressed

## Registering listeners
- Need to associate the listener class with the widget
- Two Steps
  - Create an instance of the class
  - Register it with the view using the `setOnClickListener` method 
