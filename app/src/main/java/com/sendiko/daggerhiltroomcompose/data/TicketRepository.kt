package com.sendiko.daggerhiltroomcompose.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TicketRepository @Inject constructor(private val ticketDao: TicketDao) {

    fun getAllTickets(): Flow<List<Ticket>>{
        return ticketDao.getAllTickets()
    }

    suspend fun saveTicket(ticket: Ticket) {
        return ticketDao.insertTicket(ticket)
    }

    suspend fun removeTicket(ticket: Ticket) {
        return ticketDao.deleteTicket(ticket)
    }

}