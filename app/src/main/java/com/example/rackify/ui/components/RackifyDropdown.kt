package com.example.rackify.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rackify.ui.theme.RackifyDarkGray
import com.example.rackify.ui.theme.RackifyLightGray
import com.example.rackify.ui.theme.RackifyOrange
import com.example.rackify.ui.theme.RackifyWhite
import com.example.rackify.ui.theme.Ubuntu

@Composable
fun RackifyDropdown(
    label: String,
    selectedValue: String,
    placeholder: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = RackifyLightGray,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )

        Box {
            OutlinedTextField(
                value = selectedValue.ifEmpty { placeholder },
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                textStyle = TextStyle(
                    fontFamily = Ubuntu,
                    fontSize = 12.sp,
                    color = if (selectedValue.isEmpty()) RackifyLightGray else RackifyWhite
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = RackifyDarkGray,
                    unfocusedContainerColor = RackifyDarkGray,
                    focusedBorderColor = RackifyOrange,
                    unfocusedBorderColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable { expanded = true }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(RackifyDarkGray)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option, color = RackifyWhite, fontSize = 12.sp) },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
