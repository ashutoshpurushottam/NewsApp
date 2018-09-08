# News App

A simple Android app developed for teaching purpose. The application displays news from NYTimes API. The application has been developed to teach concepts like:
  - Dependency Injection using dagger.
  - Rx (Single, Observable, RxRelay) etc. 
  - Conductor as a replacement of fragment
  - Unit and UI testing using Mockito and Espresso.
 
 ***Note: The application would remain under development for few months as it will be used to teach new concepts to my students.*** 
  

# Dependency Injection

  - The application uses application level, activity level and screen level components. **The application is written in a way that the components are not recreated on configation change**. 

# ViewModel

  - To demonstrate concepts like RxRelay the application does not use Lifecycle component. Behaviour relays are used in place of Lifecycle components. 