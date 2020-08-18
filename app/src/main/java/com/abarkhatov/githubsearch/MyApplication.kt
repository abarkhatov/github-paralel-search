package com.abarkhatov.githubsearch

import android.app.Application

import com.abarkhatov.githubsearch.di.component.AppComponent
import com.abarkhatov.githubsearch.di.component.DaggerAppComponent
import com.abarkhatov.githubsearch.di.modules.DatabaseModule
import com.abarkhatov.githubsearch.di.modules.RemoteModule

class MyApplication: Application() {
    companion object {

        private var component: AppComponent? = null


        fun getComponent(): AppComponent? {
            return component
        }
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule(applicationContext))
            .build()



    }




}
