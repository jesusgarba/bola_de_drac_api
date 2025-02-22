package com.example.myapplication.presentation.detailScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun DetailScreen(id: Int, navigateBack: () -> Unit){
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {navigateBack()}) {
            Text("Volver")
        }
        Spacer(modifier = Modifier.weight(1f))
        Text("Detalle del personaje $id", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.weight(1f))
    }



}