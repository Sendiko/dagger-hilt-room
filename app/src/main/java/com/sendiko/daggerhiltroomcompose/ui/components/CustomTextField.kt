package com.sendiko.daggerhiltroomcompose.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    hint: String,
    value: String,
    leadingIcon: ImageVector,
    onNewValue: (String) -> Unit,
    onClearValue: () -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        shape = RoundedCornerShape(100),
        onValueChange = { onNewValue(it) },
        placeholder = {
            Text(text = hint)
        },
        leadingIcon = {
            Icon(imageVector = leadingIcon, contentDescription = null)
        },
        trailingIcon = {
            if (value.isNotBlank()){
                IconButton(
                    onClick = onClearValue,
                    content = {
                        Icons.Rounded.Clear
                    }
                )
            }
        }
    )
}