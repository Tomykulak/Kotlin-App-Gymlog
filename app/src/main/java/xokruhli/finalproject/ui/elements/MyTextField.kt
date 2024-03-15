package xokruhli.finalproject.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    error: String
){
    var tfValue by rememberSaveable { mutableStateOf(value) }

    OutlinedTextField(
        value = tfValue,
        onValueChange = { newValue ->
            tfValue = newValue
            onValueChange(newValue)
        },
        label = { Text(text = hint) },
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(10.dp),
    )

    if (error.isNotEmpty()) {
        Text(text = error)
    }

}