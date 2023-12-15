package com.sendiko.daggerhiltroomcompose.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AirplaneTicket
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Stadium
import androidx.compose.material.icons.rounded.Stars
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sendiko.daggerhiltroomcompose.R
import com.sendiko.daggerhiltroomcompose.ui.components.CustomDialog
import com.sendiko.daggerhiltroomcompose.ui.components.CustomTextField
import com.sendiko.daggerhiltroomcompose.ui.components.TicketCard
import com.sendiko.daggerhiltroomcompose.ui.components.poweredBy
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.rememberMessageBarState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value
    val messageBarState = rememberMessageBarState()
    val bottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    ContentWithMessageBar(messageBarState = messageBarState) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        coroutineScope.launch { bottomSheetState.show() }
                    },
                    content = {
                        Row(
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Add,
                                contentDescription = "Add Ticket"
                            )
                        }
                    },
                )
            },
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Dagger-Hilt-Room")
                    },
                    scrollBehavior = scrollBehavior,
                    actions = {
                        IconButton(
                            onClick = {
                                viewModel.onEvent(HomeScreenEvents.OnShowDialog(!state.isShowingDialog))
                            },
                            content = {
                                Icon(
                                    imageVector = Icons.Rounded.MoreVert,
                                    contentDescription = "More"
                                )
                            }
                        )
                    }
                )
            },
        ) {
            AnimatedVisibility(visible = state.isShowingDialog) {
                CustomDialog(
                    title = "About Us",
                    image = R.drawable.logo_long,
                    description = poweredBy,
                    onConfirmAction = { viewModel.onEvent(HomeScreenEvents.OnShowDialog(!state.isShowingDialog)) },
                    onDismissRequest = {
                        viewModel.onEvent(HomeScreenEvents.OnShowDialog(!state.isShowingDialog))
                    }
                )
            }
            if (bottomSheetState.isVisible) {
                ModalBottomSheet(
                    onDismissRequest = {
                        coroutineScope.launch { bottomSheetState.hide() }
                    },
                    content = {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Insert your ticket",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            CustomTextField(
                                modifier = Modifier.fillMaxWidth(),
                                hint = "Insert Concert Name",
                                value = state.concertName,
                                leadingIcon = Icons.Rounded.Stadium,
                                onNewValue = { newValue ->
                                    viewModel.onEvent(HomeScreenEvents.OnConcertNameInput(newValue))
                                },
                                onClearValue = {
                                    viewModel.onEvent(HomeScreenEvents.OnClearValue("concert_field"))
                                }
                            )
                            CustomTextField(
                                modifier = Modifier.fillMaxWidth(),
                                hint = "Insert Artist Name",
                                value = state.artistPerforming,
                                leadingIcon = Icons.Rounded.Stars,
                                onNewValue = { newValue ->
                                    viewModel.onEvent(HomeScreenEvents.OnArtistNameInput(newValue))
                                },
                                onClearValue = {
                                    viewModel.onEvent(HomeScreenEvents.OnClearValue("artist_field"))
                                }
                            )
                            CustomTextField(
                                modifier = Modifier.fillMaxWidth(),
                                hint = "Insert Ticket Name",
                                value = state.ticketName,
                                leadingIcon = Icons.Rounded.AirplaneTicket,
                                onNewValue = { newValue ->
                                    viewModel.onEvent(HomeScreenEvents.OnTicketNameInput(newValue))
                                },
                                onClearValue = {
                                    viewModel.onEvent(HomeScreenEvents.OnClearValue("ticket_field"))
                                }
                            )
                            CustomTextField(
                                modifier = Modifier.fillMaxWidth(),
                                hint = "Insert Concert Date",
                                value = state.date,
                                leadingIcon = Icons.Rounded.CalendarToday,
                                onNewValue = { newValue ->
                                    viewModel.onEvent(HomeScreenEvents.OnDateInput(newValue))
                                },
                                onClearValue = {
                                    viewModel.onEvent(HomeScreenEvents.OnClearValue("date_field"))
                                }
                            )
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    viewModel.onEvent(HomeScreenEvents.OnSaveTicket)
                                    coroutineScope.launch {
                                        bottomSheetState.hide()
                                        !bottomSheetState.isVisible
                                    }
                                },
                                content = {
                                    Text(text = "Save my ticket")
                                }
                            )
                        }
                    }
                )
            }
            LazyColumn(
                contentPadding = it,
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                content = {
                    items(state.ticketList) { ticket ->
                        TicketCard(
                            modifier = Modifier.padding(8.dp),
                            uid = ticket.uid,
                            ticketName = ticket.ticketName,
                            concertName = ticket.concertName,
                            artistPerforming = ticket.artistPerforming,
                            date = ticket.date,
                            onDelete = {
                                viewModel.onEvent(HomeScreenEvents.OnDeleteTicket(ticket))
                            }
                        )
                    }
                }
            )
        }
    }
}