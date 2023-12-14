package com.sendiko.daggerhiltroomcompose.ui.screens

import com.sendiko.daggerhiltroomcompose.data.Ticket

sealed class HomeScreenEvents {
    data class OnTicketNameInput(val name: String): HomeScreenEvents()
    data class OnConcertNameInput(val name: String): HomeScreenEvents()
    data class OnArtistNameInput(val name: String): HomeScreenEvents()
    data class OnDateInput(val date: String): HomeScreenEvents()
    data class OnClearValue(val field: String): HomeScreenEvents()
    data class OnDeleteTicket(val ticket: Ticket): HomeScreenEvents()
    data object OnSaveTicket: HomeScreenEvents()
}