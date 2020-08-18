package com.abarkhatov.githubsearch.repository.local;

import androidx.room.RoomDatabase;

import com.abarkhatov.githubsearch.repository.local.dao.FavoriteRepositoryDao;
import com.abarkhatov.githubsearch.repository.local.entity.FavoriteRepo;

@androidx.room.Database(entities = {FavoriteRepo.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    public abstract FavoriteRepositoryDao favoriteRepositoryDao();
}