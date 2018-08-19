# Android Architecture CountryApp Sample
During Google I/O 2017, Android Team announced guidelines for architecture of Android app. 
This is just sample app explaining the new Architecture Guidelines.

## Components Demonstrated
* [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Room](https://developer.android.com/topic/libraries/architecture/room)

## What's the app about?
It's a simple app that uses [RestCountries APIs](https://restcountries.eu) and displays a list of countries.

## Architecture
The app uses ViewModel to abstract the data from UI and Repository as single source of truth for data. 
Repository fetches the data from database, if it doesn't exist then it fetches from the webservice.

![alt text](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)
