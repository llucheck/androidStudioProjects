# Shared Preferences

## Saving State

- The state of an Android activity is
  - the property values of the controls currently displayed
  - any other information required to get the activity back to its current condition
- State information is lost when the activity is closed or when the screen is rotated
- Two options for saving state information
  - Store data only for application lifetime  by saving to the instance state bundle
  - Store data between application instances by using shared preferences


## Persistent Storage
- This data is written to the database on the device and is available all the time
- Data storage options
  - SharedPreferences
  - Internal storage
  - External storage
  - SQLite databases
  - Network connection
- [Android Developer - Storage Options](https://developer.android.com/guide/topics/data/data-storage.html)

### SharedPreferences
- Allows you to save and retrieve persistent key-value pairs of primitive data types
- Methods
  - `getSharedPreferences()` :  multiple preferences files
  - `getPreferences()` only one preferences file

## Application Settings
- Using Android's [Preference API](https://developer.android.com/guide/topics/ui/settings.html) simplifies saving user application preferences
