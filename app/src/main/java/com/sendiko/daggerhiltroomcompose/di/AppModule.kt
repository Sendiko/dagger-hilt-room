package com.sendiko.daggerhiltroomcompose.di

import android.content.Context
import androidx.room.Room
import com.sendiko.daggerhiltroomcompose.data.AppDatabase
import com.sendiko.daggerhiltroomcompose.data.TicketDao
import com.sendiko.daggerhiltroomcompose.data.TicketRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun proviceAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "ticket_db")
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase): TicketDao {
        return appDatabase.dao()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(ticketDao: TicketDao): TicketRepository{
        return TicketRepository(ticketDao)
    }

}