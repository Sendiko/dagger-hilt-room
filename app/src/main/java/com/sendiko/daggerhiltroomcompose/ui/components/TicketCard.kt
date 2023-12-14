package com.sendiko.daggerhiltroomcompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TicketCard(
    modifier: Modifier = Modifier,
    uid: String,
    ticketName: String,
    concertName: String,
    artistPerforming: String,
    date: String,
    onDelete: () -> Unit,
) {
    OutlinedCard(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = concertName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                IconButton(
                    onClick = onDelete,
                    content = {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "Delete Ticket"
                        )
                    }
                )
            }
            Text(text = date, fontStyle = FontStyle.Italic)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = artistPerforming,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(text = ticketName, fontStyle = FontStyle.Italic)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = uid)
        }
    }
}