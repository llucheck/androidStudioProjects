# Content Providers
- One of the major app components
- Databases are usually private but a Content Provider is a class that sits between an application and its data source and its job is to provide easily managed access to access, use and modify a single data source securely
- Single class through which all data access can be made

## Reasons to Use Content Providers
1. Easily change underlying data source
2. Leverage functionality of Android classes (e.g. loaders)
3. Allows many apps to access a single data source


## General Steps for Using a ContentProvider
- Get permission to use the ContentProvider.
- Get the ContentResolver
- Pick one of four basic actions on the data: query, insert, update, delete
- Identify the data you are reading or manipulating to create a URI
- In the case of reading from the ContentProvider, display the information in the UI

## Content Resolver
- Intermediary between app and content providers
- A content resolver is required to access a content provider
-  Specify data using a URI
	-  starts with `content://` (provider prefix) which is followed by the content authority and a specific path
	-  ex: ` content://com.android.calendar/calendars`
- The Content resolver query method return a cursor,
	- Since we are working with a database we should call off the main thread.
- **Always close cursors to prevent memory leaks**


## Calendar Lab (existing code)

- Read calendar permission is in manifest
- Main activity requests permission
- Will store calendar names and ids in parallel arrays
- Spinner is populated with strings from the `calDisplayNames` array



## Content Provider Resources
- [Content Provider](https://developer.android.com/guide/topics/providers/content-providers.html)
- [Calendar Provider](https://developer.android.com/guide/topics/providers/calendar-provider.html)
