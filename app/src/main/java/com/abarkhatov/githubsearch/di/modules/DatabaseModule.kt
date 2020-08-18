package com.abarkhatov.githubsearch.di.modules

import android.content.Context
import androidx.room.Room
import com.abarkhatov.githubsearch.repository.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule(appContext: Context) {
    private val appContext: Context

    @Provides
    @Singleton
    fun provideDatabase(): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "app_db"
        )
            .build()
    }

    init {
        this.appContext = appContext
    }
}
