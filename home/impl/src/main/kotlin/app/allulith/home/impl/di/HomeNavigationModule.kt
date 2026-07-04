package app.allulith.home.impl.di

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import app.allulith.home.impl.homeNavigationBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
internal object HomeNavigationModule {

    @IntoSet
    @Provides
    fun provideHomeNavigation() : EntryProviderScope<NavKey>.(NavBackStack<NavKey>) -> Unit = { backStack ->
        homeNavigationBuilder(backStack = backStack)
    }
}
