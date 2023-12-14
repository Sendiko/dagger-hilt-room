package com.sendiko.daggerhiltroomcompose.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ticket")
data class Ticket(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val uid: String,
    val ticketName: String,
    val concertName: String,
    val artistPerforming: String,
    val date: String,
)
