# Compose Navigation triggered from ViewModel

An tiny example app build with Jetpack Compose in which navigation is triggered from the `ViewModel`.

This example implements logic so that the `ViewModel` can call `navigate(route)`, `navigateUp()` and `popUpto(route)`. This example abstracts away most navigation logic from the `View` and `ViewModel`. We use
*state* to navigate, but one could use *events* if prefered.

## Alternatives

### Singleton Navigation Manager

Joe Birch describes a [singleton navigation manager](https://medium.com/google-developer-experts/modular-navigation-with-jetpack-compose-fda9f6b2bef7). This implementation is simpler. However, a vulnerability of this singleton navigator is that any
ViewModel (and any other class) can trigger navigation. This can cause Viewmodels in the backstack to start navigation, for example after an async action finishes. You could cause hard-to-find bugs this way.

### View in Charge

An alternative to ViewModel-controlled-navigation is to let the `View` control and start navigation.
However, that would often mean that the `View` must wait on the `ViewModel` to finish something async
before navigating. It results in a state of thruth that lies inbetween `ViewModel` and View, instead
of solely in the `ViewModel`.

## Events up, state down

'Events up, state down' is
[Google's suggested way of communicating from ViewModel to View](https://developer.android.com/jetpack/guide/ui-layer#udf).
This is what is used here, though the same logic can easily be used with events.

'Events up, state down' means that from the `View` to te `ViewModel` we communicate using events / functions like (onXClicked). From the `ViewModel` to the `View` we expose `(View)State`
only. In our case, this requires a callback from the View to the `ViewModel` to notify that the
navigation has been done and state should be updated. This is needed, sadly, because the (Jetpack Compose)
navigation library uses events, not state.

## To use this in your project

Copy paste these classes into your project:

- [RouteNavigator](./app/src/main/java/nl/frank/vmnc/ui/nav/RouteNavigator.kt) the class ViewModels use to navigate.
- [NavRoute](./app/src/main/java/nl/frank/vmnc/ui/nav/NavRoute.kt) the super class for your routes
- [NavigationState](./app/src/main/java/nl/frank/vmnc/ui/nav/NavigationState.kt) the state model class

Then use them:

- Your ViewModel must implement a
  RouteNavigator interface, [by delegation](https://kotlinlang.org/docs/delegation.html), as done
  in [HomeViewModel](./app/src/main/java/nl/frank/vmnc/ui/home/HomeViewModel.kt).
- Your component's Route must
  implement [NavRoute](./app/src/main/java/nl/frank/vmnc/ui/nav/NavRoute.kt), as done with arguments in
  in [ContentPage](./app/src/main/java/nl/frank/vmnc/ui/content/ContentPage.kt).
