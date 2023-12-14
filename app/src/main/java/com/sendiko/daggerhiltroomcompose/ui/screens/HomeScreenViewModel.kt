package com.sendiko.daggerhiltroomcompose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.daggerhiltroomcompose.data.Ticket
import com.sendiko.daggerhiltroomcompose.data.TicketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: TicketRepository
): ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    private val _tickets = repository.getAllTickets()
    val state = combine(
        flow = _state,
        flow2 = _tickets,
        transform = { alsoState, tickets ->
            alsoState.copy(
                ticketList = tickets
            )
        }
    ).stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeScreenState())

    private fun generateRandomString(length: Int): String {
        val chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        return (1..length).map { chars.random() }.joinToString("")
    }

    fun onEvent(event: HomeScreenEvents){
        when(event){
            is HomeScreenEvents.OnTicketNameInput -> _state.update {
                it.copy(ticketName = event.name)
            }
            is HomeScreenEvents.OnConcertNameInput -> _state.update {
                it.copy(concertName = event.name)
            }
            is HomeScreenEvents.OnArtistNameInput -> _state.update {
                it.copy(artistPerforming = event.name)
            }
            is HomeScreenEvents.OnDateInput -> _state.update {
                it.copy(date = event.date)
            }
            is HomeScreenEvents.OnClearValue -> _state.update {
                if (event.field == "concert_field"){
                    it.copy(concertName = "")
                }
                if (event.field == "artist_field"){
                    it.copy(artistPerforming = "")
                }
                if (event.field == "date_field"){
                    it.copy(date = "")
                }
                if (event.field == "ticket_field"){
                    it.copy(ticketName = "")
                } else it.copy()
            }
            is HomeScreenEvents.OnDeleteTicket -> viewModelScope.launch {
                repository.removeTicket(event.ticket)
            }
            HomeScreenEvents.OnSaveTicket -> viewModelScope.launch {
                repository.saveTicket(
                    ticket = Ticket(
                        ticketName = state.value.ticketName,
                        concertName = state.value.concertName,
                        artistPerforming = state.value.artistPerforming,
                        date = state.value.date,
                        uid = generateRandomString(8)
                    )
                )
                _state.update {
                    it.copy(
                        ticketName = "",
                        concertName = "",
                        artistPerforming = "",
                        date = "",
                    )
                }
            }

        }
    }
}