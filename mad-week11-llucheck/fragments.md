# Fragments
- App components that represent a self-contained portion of user interface in an activity
	- Mini-activities
- Allow us to modularize our activities
- Can dynamically add and remove fragments while app is running
- Fragments are designed to be placed, alone or in groups, in a single activity

## Advantages of using fragments
- Easy to reuse components in different layouts
- Can use fragments to support different devices and orientations

## Important points about Fragments
- A fragment has its own layout
- A fragment has its own behavior with its own lifecycle callbacks.
- You can add or remove fragments in an activity while the activity is running.
- You can combine multiple fragments in a single activity to build a multi-pane UI.
- A fragment can be used in multiple activities.
- A fragment can implement a behavior that has no user interface component.
- Fragments may only be used as part of an activity and cannot be instantiated as standalone application elements

## Fragment Lifecycle
- Fragments must be embedded in an activity
- Fragments have their own lifecycle which is affected by the host lifecycle
	- As the host activity moves through its callbacks the fragment does too
	- Can put code in either the host or the fragment
- A fragment has some additional lifecycle callbacks
	- `onCreateView`  instead of `onCreate`
	-  onDestroyView`

## Fragment Naming Convention
- The class name should be in CamelCase.
	- Ex: `SignInFragment`
- The corresponding XML file should follow this naming convention `fragment_<FRAGMENT_NAME>.xml`
	- Ex: `fragment_sign_in.xml`
- For a full list of Android naming conventions, checkout [android-guidelines/project_and_code_guidelines.md at master · ribot/android-guidelines · GitHub](https://github.com/ribot/android-guidelines/blob/master/project_and_code_guidelines.md).

## Adding a fragment
- Create a Layout
- Create a Java class that extends the `Fragment` class
	- Use the support library
	- Include default constructor
	- Also include `onCreateView` method
		- returns the inflated layout
- Add a frame layout to the host activity layout
- In the host activity add the fragment using the fragment manager and fragment transaction

## Fragment Manager and Transaction
- Fragment Manager is used to add, remove and replace fragments
- You’ll need a container view to to reference and hold each location
	- Usually an empty `FrameLayout`
- If the fragment is static, you do not need a container


## Fragment Arguments
- Avoid retrieving values from host activity
- Fragments have arguments which are passed in a Bundle object that contains key-value pairs


## Support Library
- Can update your app by updating the version of the support library
  - New features could be added to the fragment support  library
  - Bug fixes
- No significant downsides to using support library
  - May increase the size of your application
  - Size of library is small < 1MB

### SnackBar vs. Toast
- Toast
  - Can’t be dismissed by swiping   
  - Activity not required
  - No user interaction
  - Good for showing info messages to user                               
- SnackBar
  - Can dismiss by swiping  
  - Can show inside an activity of your app
  - Allows for user input
  - Good for showing warning/info type messages to user that needs attention

---
## Course Rating Fragment Lab

### End Result
 The main activity (CourseRatingListActivity) will represent a list of CourseRatings using different presentations for handset and tablet-size devices. On handsets, the activity will present a list of items, which when touched, lead to a CourseRatingDetailActivity representing item details. On tablets, the activity will present the list of items and  item details side-by-side using two vertical panes.

### Starting Code
- `CourseRating` is the model class
- `CourseRatingAdapter` is the Firebase recycler view adapter class
- `CourseRatingListActivity` authenticates and displays a list of course ratings from a Firebase realtime database
- `CourseRatingDetailActivity` hosts a frame layout which is currently empty.  It will be used to host a fragment that displays the detail of course rating.  
  - It is only used on phones.  On tablets, the detail will be displayed next to the list.
- `CourseRatingFragment` extends the support library version of the Fragment class and contains most of the code that was previously in the detail activity.
  - it contains a constant string which will be used to pass as the key for the CourseReview id when it is passed between activities
  - it also has an empty default constructor
  - Uses a [SnackBar](https://developer.android.com/reference/android/support/design/widget/Snackbar.html)
