# NewsApp-MVVM-Architecture
Introducing our cutting-edge News MVVM app, meticulously crafted with the latest technologies recommended by Google. This app is designed to deliver a seamless news reading experience, whether you're online or offline. Here’s a sneak peek into its features and architecture:

### Major Highlights:
- **MVVM Architecture** : Built using the Model-View-ViewModel architecture, ensuring a clean separation of concerns and making the app highly maintainable and testable.
- **Offline-First**: Prioritizes offline access with a single source of truth, ensuring you can read the news even without an internet connection.
- **Room Database**: Utilizes Room for efficient local data storage and retrieval.
- **Dependency Injection with Hilt**: Implements Hilt for managing dependencies, reducing boilerplate code and improving scalability.
- **Pagination Library**: Seamlessly handles data pagination, providing a smooth scrolling experience through endless news articles.
- **Coroutines and Flow API**: Leverages Kotlin Coroutines and Flow API for efficient and smooth data processing pipelines.
- **Comprehensive Unit Testing**: Ensures robustness with extensive unit tests covering all critical components.

<p align="center">
<img alt="mvvm-architecture"  src="https://files.codingninjas.in/article_images/android-mvvm-model-view-viewmodel-architecture-0-1647677770.webp">
</p>

## Feature implemented:
- NewsApp
- Instant search using Flow operators
  - Debounce
  - Filter
  - DistinctUntilChanged
  - FlatMapLatest
- Offline news
- Pagination
- Unit Test
  - Mockito
  - Turbine https://github.com/cashapp/turbine
  - Espresso
- TopHeadlines of the news
- Country-wise news
- Multiple Languages selection-wise news
- Multiple sources wise news

## Dependency Used:
- Recycler View for listing
```
implementation "androidx.recyclerview:recyclerview:1.2.1"
implementation 'androidx.recyclerview:recyclerview-selection:1.1.0' //multi item selection
```
- Glide for image loading
```
implementation 'com.github.bumptech.glide:glide:4.11.0'
```
- Retrofit for networking
```
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
implementation 'com.squareup.okhttp3:logging-interceptor:4.8.0'
```
- Android Lifecycle aware component
```
implementation 'android.arch.lifecycle:extensions:1.1.1'
implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1'
implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.5.1'
```
- Dagger for dependency Injection
```
implementation "com.google.dagger:dagger:2.42"
kapt "com.google.dagger:dagger-compiler:2.42"
```
- For WebView browser
```
implementation 'androidx.browser:browser:1.4.0'
```
- Room Database
```
implementation "androidx.room:room-runtime:2.4.2"
kapt "androidx.room:room-compiler:2.4.2"
// optional - Kotlin Extensions and Coroutines support for Room
implementation "androidx.room:room-ktx:2.4.2"
```

- Paging library
```
implementation "androidx.paging:paging-runtime:3.1.1"
```
- Local Unit test
```
testImplementation 'junit:junit:4.13.2'
testImplementation "org.mockito:mockito-core:4.9.0"
kaptTest "com.google.dagger:dagger-compiler:2.42"
testImplementation "android.arch.core:core-testing:1.1.1"
testImplementation "org.hamcrest:hamcrest-library:2.1"
testImplementation 'org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1'
testImplementation 'app.cash.turbine:turbine:0.12.1'
testImplementation 'androidx.test.espresso:espresso-core:3.5.0'
```

- UI Test
```
androidTestImplementation 'androidx.test.ext:junit:1.1.3'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.0'
androidTestImplementation "androidx.test.espresso:espresso-contrib:3.4.0"
androidTestImplementation "androidx.test.espresso:espresso-intents:3.4.0"
androidTestImplementation "org.mockito:mockito-core:4.9.0"
androidTestImplementation 'org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1'
androidTestImplementation "org.mockito:mockito-core:4.9.0"
kaptAndroidTest "com.google.dagger:dagger-compiler:2.42"
```
## The Complete Project Folder Structure
```
├── NewsApplication.kt
├── data
│   ├── api
│   ├── local
│   ├── model
│   └── repository
├── di
│   ├── component
│   ├── module
│   ├── qualifiers.kt
│   └── scopes.kt
├── ui
│   ├── MainActivity.kt
│   ├── base
│   ├── country
│   ├── language
│   ├── newsListScreen
│   ├── pagination
│   ├── search
│   ├── sources
│   └── topheadline
└── utils
    ├── AppConstant.kt
    ├── DispatcherProvider.kt
    ├── Extentions.kt
    ├── NetworkHelper.kt
    ├── Resource.kt
    ├── Status.kt
    ├── TypeAliases.kt
    └── network

```

<p align="center">
<img alt="main_screen" src="https://github.com/aman1sr/NewsApp-MVVM-Architecture/blob/master/app/screenshot/newsHome.jpeg" width="360"  height="640"> &nbsp;&nbsp;&nbsp;&nbsp;
<img alt="top-top_headline" src="https://github.com/aman1sr/NewsApp-MVVM-Architecture/blob/master/app/screenshot/newsTopHeadline.jpeg" width="360"  height="640" marginLeft="20">
</p>

<p align="center">
<img alt="search1.1" src="https://github.com/aman1sr/NewsApp-MVVM-Architecture/blob/master/app/screenshot/newsSearch1.1.jpeg" width="360"  height="640"  marginLeft="20">
<img alt="search2" src="https://github.com/aman1sr/NewsApp-MVVM-Architecture/blob/master/app/screenshot/newsSearch2.jpeg" width="360"  height="640"  marginLeft="20">
<img alt="search2.2" src="https://github.com/aman1sr/NewsApp-MVVM-Architecture/blob/master/app/screenshot/newsSearch1.jpeg" width="360"  height="640"  marginLeft="20">
</p>


