package com.sendiko.daggerhiltroomcompose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketDao {

    @Query("SELECT * FROM ticket")
    fun getAllTickets(): Flow<List<Ticket>>

    @Insert(onConflict = REPLACE)
    suspend fun insertTicket(ticket: Ticket)

    @Delete
    suspend fun deleteTicket(ticket: Ticket)
}