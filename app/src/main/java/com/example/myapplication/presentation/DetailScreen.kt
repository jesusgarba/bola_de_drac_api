package com.example.myapplication.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.presentation.model.Character
import com.example.myapplication.presentation.model.Transformation
import com.example.myapplication.ui.theme.ColorCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    id: Int,
    bolaDracApiViewModel: BolaDracApiViewModel,
    navigateBack: () -> Unit,
) {
    LaunchedEffect(id) {
        bolaDracApiViewModel.getCharacterDetail(id)
    }

    val detailState by bolaDracApiViewModel.characterDetailState.collectAsState()
    val title = (detailState as? CharacterDetailUiState.Success)?.character?.name ?: "Detalle"

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                title = {
                    Text(text = title, fontWeight = FontWeight.Bold)
                }
            )
        }
    ) { padding ->
        when (val state = detailState) {
            CharacterDetailUiState.Loading -> DetailLoading(padding)
            is CharacterDetailUiState.Error -> DetailError(
                message = state.message,
                padding = padding,
                onRetry = { bolaDracApiViewModel.getCharacterDetail(id) }
            )

            is CharacterDetailUiState.Success -> CharacterDetail(
                character = state.character,
                padding = padding
            )
        }
    }
}

@Composable
private fun DetailLoading(padding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = ColorCard)
    }
}

@Composable
private fun DetailError(
    message: String,
    padding: PaddingValues,
    onRetry: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text(text = "Reintentar")
        }
    }
}

@Composable
private fun CharacterDetail(
    character: Character,
    padding: PaddingValues,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(padding)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CharacterHeader(character)
        CharacterStats(character)
        DetailSection(title = "Descripcion") {
            Text(
                text = character.description.orEmpty(),
                color = Color.DarkGray,
                lineHeight = 20.sp
            )
        }
        character.originPlanet?.let { planet ->
            DetailSection(title = "Planeta de origen") {
                Text(
                    text = planet.name.orEmpty(),
                    color = ColorCard,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (!planet.image.isNullOrBlank()) {
                    AsyncImage(
                        model = planet.image,
                        contentDescription = planet.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                if (!planet.description.isNullOrBlank()) {
                    Text(
                        text = planet.description,
                        color = Color.DarkGray,
                        lineHeight = 20.sp
                    )
                }
            }
        }
        if (!character.transformations.isNullOrEmpty()) {
            DetailSection(title = "Transformaciones") {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    character.transformations.forEach { transformation ->
                        TransformationItem(transformation)
                    }
                }
            }
        }
    }
}

@Composable
private fun CharacterHeader(character: Character) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = ColorCard)
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 260.dp, max = 360.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = character.name.orEmpty(),
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = listOfNotNull(character.race, character.gender)
                    .filter { it.isNotBlank() }
                    .joinToString(" - "),
                color = Color.White.copy(alpha = 0.82f),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun CharacterStats(character: Character) {
    DetailSection(title = "Datos") {
        InfoRow(label = "Ki", value = character.ki)
        InfoRow(label = "Max Ki", value = character.maxKi)
        InfoRow(label = "Afiliacion", value = character.affiliation)
    }
}

@Composable
private fun DetailSection(
    title: String,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F5FA))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                color = ColorCard,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            content()
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String?) {
    if (value.isNullOrBlank()) return

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color.Gray)
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = value,
            color = Color.DarkGray,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.End
        )
    }
}

@Composable
private fun TransformationItem(transformation: Transformation) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = transformation.image,
            contentDescription = transformation.name,
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = transformation.name.orEmpty(),
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold
            )
            if (!transformation.ki.isNullOrBlank()) {
                Text(
                    text = transformation.ki,
                    color = Color.Gray,
                    fontSize = 13.sp
                )
            }
        }
    }
}
