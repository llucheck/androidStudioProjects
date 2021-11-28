# Parcelable

## Bundles
- A Bundle object which is used to pass data to Android components is a key/value store for specialized objects.
- You can place the following objects types into a Bundle:
  - String
  - primitives
  - Serializable
  - Parcelable
- To pass objects via a Bundle, you should implement the `Parcelable` interface.

## The Parceable Interface
- Parcelable is the Android implementation of the Java `Serializable`
  - it can be processed relatively fast, compared to the standard Java serialization
- Requires 2 methods
  - The `writeToParcel` method is where you add all your class data to the parcel.
  - `describeContents` is required but not generally used
- You will also want a constructor that takes a Parceable object as an argument and returns a class object
- Also requires a static Creator class


 ```java
  public static final Parcelable.Creator<YourClass> CREATOR = new Parcelable.Creator<YourClass>() {
  public YourClass createFromParcel(Parcel in) {
   return new YourClass(in);
  }

  public YourClass[] newArray(int size) {
   return new YourClass[size];
  }
};
```    

- [Parseable Generator](http://www.parcelabler.com)
