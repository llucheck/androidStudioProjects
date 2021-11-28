# Menus

## Action Bar
- Also called the app bar
- References:
  - http://developer.android.com/reference/android/app/ActionBar.html
  - http://developer.android.com/training/appbar/index.html

### Hiding the ActionBar
If you don't want the action bar for a particular activity, you can hiding it using the following code

```java
ActionBar actionBar = getSupportActionBar();
actionBar.hide();
```
- Can also hide by modifying the app's theme

## Menus
- Menus are defined in various callback methods
  - `onCreateOptionMenu`
  - `onCreateContextMenu`
- Menus handle their own events so you don't need to create and register listener
- Menu types:
  - **Options** menus provide actions and operations for the app's current screen and are accessed through the ActionBar
  - **Context** menus display options related to the object that is being pressed
  - **Popup** Menus

### Options Menu
- Define menu options in XML and inflate in the `OnCreateOptionsMenu` method.
- Respond to user selections in the the` onOptionsItemSelected` method

### Defining Menus in XML
- Menu files are kept in `res/menu`
- Use File->New->Android resource file to add a new menu and select Menu for resource type
- Use the `<item>` element to create a menu option
- Some attributes that  you can use to define an item's appearance and behavior:
  - `android:id`
  - `android:icon`
  - `android:title`
  - `android:showAsAction`
