package app.allulith.data.api.di

import android.content.Context
import app.allulith.data.api.OrganiserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideOrganiserDatabase(@ApplicationContext context: Context): OrganiserDatabase {
        return OrganiserDatabase.getInstance(context)
    }
}
