package app.allulith.goals.impl.di

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import app.allulith.goals.impl.goalsNavigationBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
internal object GoalsNavigationModule {

    @IntoSet
    @Provides
    fun providesGoalsNavigation() : EntryProviderScope<NavKey>.(NavBackStack<NavKey>) -> Unit = { backStack ->
        goalsNavigationBuilder(backStack = backStack)
    }
}
