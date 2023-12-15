package com.sendiko.daggerhiltroomcompose.ui.screens

import com.sendiko.daggerhiltroomcompose.data.Ticket

data class HomeScreenState(
    val ticketName: String = "",
    val concertName: String = "",
    val artistPerforming: String = "",
    val date: String = "",
    val ticketList: List<Ticket> = emptyList(),
    val isShowingDialog: Boolean = false,
)
