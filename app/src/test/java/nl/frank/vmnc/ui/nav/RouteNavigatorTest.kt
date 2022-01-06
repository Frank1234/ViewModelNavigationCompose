package nl.frank.vmnc.ui.nav

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class RouteNavigatorTest {

    @Test
    fun `WHEN navigate is called THEN state is set correctly`() = runTest {
        val routeNavigator = MyRouteNavigator()
        val state = NavigationState.NavigateToRoute("123")
        routeNavigator.navigate(state)
        assertEquals(state, routeNavigator.navigationState.value)
    }

    @Test
    fun `WHEN navigate is called AND THEN onNavigated is called THEN state is Idle`() = runTest {
        val routeNavigator = MyRouteNavigator()
        val state = NavigationState.NavigateToRoute("123")
        routeNavigator.navigate(state)
        routeNavigator.onNavigated(state)
        assertEquals(NavigationState.Idle, routeNavigator.navigationState.value)
    }

    @Test
    fun `WHEN navigate is called AND THEN onNavigated is called with different route THEN state is not Idle`() =
        runTest {
            val routeNavigator = MyRouteNavigator()
            val state = NavigationState.NavigateToRoute("123")
            routeNavigator.navigate(state)
            routeNavigator.onNavigated(NavigationState.NavigateToRoute("456"))
            assertEquals(state, routeNavigator.navigationState.value)
        }

    @Test
    fun `WHEN navigate is called AND THEN onNavigated is called with different instance of same route THEN state is not Idle`() =
        runTest {
            val routeNavigator = MyRouteNavigator()
            val state = NavigationState.NavigateToRoute("123")
            routeNavigator.navigate(state)
            routeNavigator.onNavigated(NavigationState.NavigateToRoute("123"))
            assertEquals(state, routeNavigator.navigationState.value)
        }
}