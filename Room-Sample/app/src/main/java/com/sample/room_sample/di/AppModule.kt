package com.sample.room_sample.di

import android.content.Context
import androidx.room.Room
import com.sample.room_sample.data.db.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTodoDatabase(
        @ApplicationContext context: Context
    ): TodoDatabase {

        return Room.databaseBuilder(
            context.applicationContext,
            TodoDatabase::class.java,
            "todo-list"
        ).build()
    }
}