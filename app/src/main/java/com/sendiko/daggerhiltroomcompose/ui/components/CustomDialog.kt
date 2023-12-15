package com.sendiko.daggerhiltroomcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomDialog(
    image: Int? = null,
    title: String,
    description: String,
    onConfirmAction: () -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        content = {
            Card(
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (image != null) {
                        Image(
                            painter = painterResource(id = image),
                            contentDescription = title,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = description, textAlign = TextAlign.Justify)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { onConfirmAction() }) {
                            Text(text = "OK")
                        }
                    }
                }
            }
        }
    )
}

const val poweredBy = "This software is made and supported by Sendiko Software Studio." +
        " You can support us by following us on Github and Instagram"