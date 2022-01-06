package nl.frank.vmnc.ui.vm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import nl.frank.vmnc.ui.nav.MyRouteNavigator
import nl.frank.vmnc.ui.nav.RouteNavigator

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun bindRouteNavigator(): RouteNavigator = MyRouteNavigator()
}