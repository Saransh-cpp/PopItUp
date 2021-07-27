# PopItUp

An Augmented Reality game, built with Google ARCore SDK and Google Sceneform SDK for Android.

## Augmented Reality
 - The `sceneform SDK` was injected externally in the app (due to the deprecation) and hence, this repository has more Java code than the Kotlin code.
 - A lot if changes were made to the `sceneform SDK` itself, to make it compatible with `AndroidX` and other new updates.
 - The app spawns 20 spheroids in the real world which the users can shoot with an AR bullet.

## LeaderBoard
 - The app provides a `Google Sign In` option using `Firebase` which is further used to store a user's data into `Firestore`.
 - Whenever the user creates a new high score (lesser the time, greater the score), the leaderboard is updated live and automatically.
 - The app uses `FirestoreRecyyclerView` to bind all the live data into a `RecyclerView`.

## Other screens
 - Other screens (or `fragments` and `activities`) are mostly static.
 - The result screen shows the time taken by the user to complete the game and also let's them know if their score was updated.
 - The about screen is static except for the AD present at the bottom,, which is spawned using `Google AdMob SDK`.
 - Home screen has 2 buttons, one to start the game and another to sign out of the app.

## App screens

![Google Pixel 4 Presentation](https://user-images.githubusercontent.com/74055102/127057273-f463bc09-7fad-4757-bacc-25c1b6876d2e.png)

![Google Pixel 4 Presentation 2](https://user-images.githubusercontent.com/74055102/127155359-8f1e1b55-34a7-4a95-9253-27de32db7f64.png)

## Gameplay

### The first working prototype


https://user-images.githubusercontent.com/74055102/127104581-e7405b7f-a8cc-4920-81cc-db5bc9b3f72f.mp4


### Gameplay of the latest version


https://user-images.githubusercontent.com/74055102/127147044-f6c4d53c-4ff6-489e-8c19-0cdad270973b.mp4



