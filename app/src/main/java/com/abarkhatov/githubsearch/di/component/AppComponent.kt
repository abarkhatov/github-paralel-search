package com.abarkhatov.githubsearch.di.component

import com.abarkhatov.githubsearch.di.modules.DatabaseModule
import com.abarkhatov.githubsearch.repository.remote.NetworkManager
import com.abarkhatov.githubsearch.di.modules.RemoteModule
import com.abarkhatov.githubsearch.mvp.presenter.MainActivityPresenter
import com.abarkhatov.githubsearch.repository.local.DatabaseManager
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(presenter:MainActivityPresenter)
    fun inject(networkManager: NetworkManager)
    fun inject(databaseManager: DatabaseManager)

}