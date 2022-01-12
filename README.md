# Compose Navigation triggered from ViewModel

An tiny example app build with Jetpack Compose in which navigation is triggered from the `ViewModel`
.

This example abstracts away most navigation logic from the `View` and `ViewModel`. This example uses
state to navigate, but one could use events if prefered.

## Alternatives

#### Singleton Navigation Manager

[Joe Birch describes a singleton navigation manager](https://medium.com/google-developer-experts/modular-navigation-with-jetpack-compose-fda9f6b2bef7)
. That implementation is simpler. However, a vulnerability of this singleton navigator is that any
ViewModel in the backstack can trigger navigation, for example after an async action finishes. It is
easy to cause hard to find bugs in this way.

#### View in Charge

An alternative to `ViewModel` controller navigation, is to let the `View` control navigation.
However, that would often mean that the view must wait on the `ViewModel` to finish something async
before navigating. It results in a state of thruth that lies inbetween `ViewModel` and View, instead
of solely in the `ViewModel`.

## In this example

This example implements logic so that the `ViewModel` can call `navigate(route)`, `navigateUp()`
and `popUpto(route)` to navigate using state.

## Events up, state down

'Events up, state down' is
Google's [suggested way of communicating from ViewModel to View.](https://developer.android.com/jetpack/guide/ui-layer#udf)
so that is what is used here. Note that Events can easily be used with the same logic.

'Events up, state down' means that from the `View` to te `ViewModel` we communicate using **
events/functions** like (onXClicked). From the `ViewModel` to the `View` we expose `(View)State`
only. In our case, this requires a call back from the View to the `ViewModel` to notify that the
navigation has been done and should be removed. This is needed, sadly, because the (jetpack compose)
navigation library uses events, not state.

## To use this in your project

Copy paste these classes into your project:

- // TODO

To use them:

- Your ViewModel must implement a
  RouteNavigator [interface by delegation](https://kotlinlang.org/docs/delegation.html), as done
  in [MainPageViewModel](./app/src/main/java/nl/frank/vmnc/ui/main/MainPageViewModel.kt).
- Your component's Route must
  implement [NavRoute](./app/src/main/java/nl/frank/vmnc/ui/nav/NavRoute.kt), as done
  in [MainPageRoute](./app/src/main/java/nl/frank/vmnc/ui/main/MainPage.kt).

// TODO ook projectje maken voor het toevoegen van transitie animaties?
