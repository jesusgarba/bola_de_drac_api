package com.example.myapplication.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Preview(showSystemUi = true)
@Composable
fun InitScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "PRUEBA",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
    ) {
        Scaffold(

            topBar = { TopBarView(drawerState, scope) },


            content = { pading ->
                Column(
                    modifier = Modifier
                        .padding(pading)
                ) {
                    Text("Content", color = Color.White)
                }
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarView(drawerState: DrawerState, scope: CoroutineScope) {
    Box(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            modifier = Modifier
                .background(Color.White)
                .shadow(elevation = 5.dp),
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }) {
                    Icon(imageVector = Icons.Rounded.Menu, contentDescription = "menu")
                }
            },
            title = { ImageAndTextAppBar() },
        )
    }
}

@Composable
fun ImageAndTextAppBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        ConfigMenu()
        Spacer(modifier = Modifier.weight(0.5f))
        Box(
            modifier = Modifier
                .height(48.dp)
                .width(48.dp), contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(id = R.drawable.icon_bola_drac),
                contentDescription = "dragon ball icon",
                contentScale = ContentScale.Fit
            )
        }

    }
}

@Composable
fun ConfigMenu() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {}) {
        //Text("characters", fontSize = 16.sp)
        ItemDropDrawMenu()
        /*     Icon(
                 modifier = Modifier.height(16.dp),
                 imageVector = Icons.Rounded.KeyboardArrowDown,
                 contentDescription = "icon"
             )*/
    }
}

@Composable
fun ItemDropDrawMenu() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("characters", "transformations", "planets")
    var selectedIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
            .background(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                expanded = true
            }) {
            Text(
                text = items[selectedIndex],
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Icon(
                modifier = Modifier.height(16.dp),
                imageVector = Icons.Rounded.KeyboardArrowDown,
                contentDescription = "icon"
            )
        }

        DropdownMenu(
            modifier = Modifier.background(Color.White),
            expanded = expanded,
            offset = DpOffset(x = (-15).dp, y = (+10).dp),
            onDismissRequest = { expanded = false },

            ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(
                    modifier = Modifier
                        .wrapContentSize()
                        .background(Color.White),
                    text = { Text(text = s, fontSize = 16.sp, fontWeight = FontWeight.Normal) },
                    onClick = {
                        selectedIndex = index
                        expanded = false
                    })
            }
        }
    }
}




