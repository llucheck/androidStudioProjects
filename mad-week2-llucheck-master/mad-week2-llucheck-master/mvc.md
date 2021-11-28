# Model-View-Controller (MVC)
- Composite design pattern
- MVC lets you separate your business logic from your User Interface, making it easier to modify either one without affecting the other

## Three elements of MVC
- Model
- View
- Controller
- You can have any number of views, and any number of controllers in your application but only a single model

## Model
- Represents the data of your application, along with the business logic
- The model can be shared among any number of view and controller objects
- In an Android application the model is implemented by one or more Java classes

## View
- The means of presenting the model's data to the outside world
- If the model data changes, the view must update its presentation as needed.
- In an Android application this is the XML layout.

## Controller
- Translates the user's interactions with the view into actions that the model will perform
- In an Android application a controller is implemented by extending the Activity or Fragment class.

## Process
- User interacts with the view
- View sends message to controller
- Controller asks the model to change its state
- The model notifies the view when its state has changed
- The controller asks the model for state
- Controller updates the view
---
# Improve QuizApp - More Questions

## Add a model class
- Create a new class named Question
- Attributes
  - textResId (`int`)
  - answerTrue (`bool`)
- Generate constructor
- Generate getters and setters

## Update the View
- Add id to the TextView and remove the question text
- Add a button with an id of `next_button` and text of Next

## Add more questions
- In the string resources file add at least three more true/false statements with a least one question with a correct answer of true and one question with a correct answer of false.

## Update the Controller
- At the class level declare two variables
  - nextButton (type: `Button`)
  - questionTextView (type: `TextView`)
- Get references to these components in `onCreate`
``- Use `findViewById` method
- At the class level add an array variable that holds references to questions and a variable to keep track of the question
- Create a method to update the question
- Call this method in onCreate after statement that retrieves reference to TextView
- Add a method to check if an answer is correct or incorrect
Rename CorrectClickListener to TrueClickListener and change the onClick method to:
- Update `TrueClickListener`
- Update `FalseClickListener`
- Add a listener class for the next button
- Register this listener with the next button in onCreate

## Add Button icon
- Copy the image folders and add to project resources
- Update the xml layout to use the images
