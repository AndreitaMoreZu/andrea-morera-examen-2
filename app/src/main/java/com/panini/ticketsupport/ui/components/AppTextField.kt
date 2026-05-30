package com.panini.ticketsupport.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.panini.ticketsupport.ui.theme.AppBorder
import com.panini.ticketsupport.ui.theme.AppPrimary
import com.panini.ticketsupport.ui.theme.AppSecondaryText

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    readOnly: Boolean = false,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = if (readOnly) { _ -> } else onValueChange,
        label = { Text(text = label, color = AppSecondaryText) },
        placeholder = { Text(text = placeholder, color = AppSecondaryText) },
        readOnly = readOnly,
        enabled = enabled,
        singleLine = singleLine,
        maxLines = maxLines,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AppPrimary,
            unfocusedBorderColor = AppBorder,
            focusedLabelColor = AppPrimary,
            cursorColor = AppPrimary,
            focusedTextColor = AppPrimary,
            unfocusedTextColor = AppPrimary
        ),
        modifier = modifier
    )
}
