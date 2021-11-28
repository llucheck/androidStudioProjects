
# Week 9 Notes

## Live Data and ViewModel

### Live Data
- [LiveData ](https://developer.android.com/topic/libraries/architecture/livedata.html) is a lifecycle aware class for data observation.
- Room generates all code required to update the `LiveData` when the database is updated.
- Use the template` LiveData<T>` where `T` is the data type

### View Model
- The ViewModel's role is to provide data to the UI and survive configuration changes.
- A ViewModel acts as a communication center between the Repository and the UI.  It completely separates the repository from the View.
- [Android Developer ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel.html)
- [ViewModel - YouTube](https://www.youtube.com/watch?v=c9-057jC1ZA)

**Implementing the `ViewModel`**
1. Create a class that extends `AndroidViewModel`
2. Add a private member variable to hold a reference to the repository
3. Add a private `LiveData` member to cache the data item
4. Add a constructor that gets a reference to the repository and
5. Create a getter method for the data item
6. Create wrappers of required CRUD methods to hide implementation from the UI

**Never pass context into ViewModel instances.** Do not store Activity, Fragment, or View instances or their Context in the ViewModel. Use `AndroidViewModel` subclass instead

### Summary - Working with `LiveData` objects

1. Create an instance of `LiveData` to hold a certain type of data in  your `ViewModel` class and also in the repository,  if one is used.
1. In your Activity class, create a `ViewModel` object
1. Using the `ViewModel` object, create an `Observer` object that defines the `onChanged()` method.  This methods controls what happens when the LiveData object's held data changes.
1. Attach the `Observer` object to the `LiveData` object using the `observe()` method. The parameters are the LifecycleOwner object (`Activity`) and the `Observer` object. This subscribes the `Observer` object to the `LiveData` object so that it is notified of changes.

---

## Design Patterns
The model is the same in all of these patterns.  The difference is how the View and Controller functions are implemented.

- Model/View/Controller (MVC)
- Model/View/Presenter (MVP) - breaks the controller up so that the natural view/activity coupling can occur without tying it to the rest of the “controller” responsibilities.  The  presenter class is essentially the controller except that it is not at all tied to the View.
- Model/View/ViewModel (MVVM) - The ViewModel is responsible for wrapping the model and preparing observable data needed by the view. The View binds to observable variables and the actions exposed by the ViewModel.

---

## Swipe to delete
- To implement swipe action use [`ItemTouchHelper`](https://developer.android.com/reference/android/support/v7/widget/helper/ItemTouchHelper.html)
- Requires a callback - using a simple callback
- Store object id in a tag which is used to store any data that we don’t want to display.
