# RecyclerView
- RecyclerView is used to display a list
- Successor to ListView


### Implementation
- In app module gradle script , add the RecyclerView library dependency
 `implementation 'androidx.recyclerview:recyclerview:1.1.0'`
- Add a RecyclerView to your layout to display the items
- Create a custom row layout XML file to visualize the item
- Create a `RecyclerView.Adapter` and `ViewHolder` to render the item
- Bind the adapter to the data source to populate the RecyclerView


## ViewHolder and Adapter
- `RecyclerViews` create `ViewHolders` which manage the views
- An adapter is a controller object that sits between the `RecyclerView` and its data set
- The adapter creates ViewHolders when necessary and binds data from the model  to the ViewHolder
