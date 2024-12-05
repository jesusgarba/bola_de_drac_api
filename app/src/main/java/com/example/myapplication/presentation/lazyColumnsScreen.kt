import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PruebaList() {
    val colors = listOf(Color.Red, Color.Blue, Color.Yellow)
    // [START android_compose_insets_m3_scaffold]
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "top app bar") })
    }) { innerPadding ->
        // innerPadding contains inset information for you to use and apply
        LazyColumn(
            // consume insets as scaffold doesn't do it by default
            modifier = Modifier.consumeWindowInsets(innerPadding),
            contentPadding = innerPadding
        ) {
            items(count = 100) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(colors[it % colors.size])
                ) {
                    Text(modifier = Modifier.padding(horizontal = 10.dp), text = "Prueba...")
                }
            }
        }
    }
    // [END android_compose_insets_m3_scaffold]

}
