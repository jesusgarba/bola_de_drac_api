package com.example.myapplication.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Settings
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.presentation.model.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


object Dimensions {
    val padding_4dp = 4.dp
    val padding_8dp = 8.dp
    val padding_16dp = 16.dp
    val padding_24dp = 24.dp
}

@Composable
fun InitScreen(bolaDracApiViewModel: BolaDracApiViewModel) {

    val characters = bolaDracApiViewModel.charactersPaging.collectAsLazyPagingItems()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        modifier = Modifier.background(Color.White),
        drawerState = drawerState,
        drawerContent = {
            Box(modifier = Modifier.width(250.dp)) {
                ModalDrawerSheet() {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(horizontal = 12.dp, vertical = 24.dp)
                    ) {
                        ChildDrawerConfig(
                            icon = {
                                Icon(
                                    imageVector = Icons.Rounded.Settings,
                                    tint = Color.Gray,
                                    contentDescription = "settings icon"
                                )
                            },
                            text = {
                                Text(text = "Perfil", color = Color.Gray)
                            }, onclick = { Log.i("Jesus", "-> Has pulsado settings") }
                        )

                        ChildDrawerConfig(
                            icon = {
                                Icon(
                                    imageVector = Icons.Rounded.Favorite,
                                    tint = Color.Gray,
                                    contentDescription = "settings icon"
                                )
                            },
                            text = {
                                Text(text = "Favorite list", color = Color.Gray)
                            }, onclick = { Log.i("Jesus", "-> Has pulsado Favorites") })

                        ChildDrawerConfig(
                            icon = {
                                Icon(
                                    painterResource(id = R.drawable.ic_round_local_cafe_24),
                                    tint = Color.Gray,
                                    contentDescription = "about us"
                                )
                            },
                            text = {
                                Text(text = "About us", color = Color.Gray)
                            }, onclick = { Log.i("Jesus", "-> Has pulsado about us") }
                        )

                        ChildDrawerConfig(
                            icon = {
                                Icon(
                                    painterResource(id = R.drawable.ic_round_policy_24),
                                    tint = Color.Gray,
                                    contentDescription = "privacy icon"
                                )
                            },
                            text = {
                                Text(text = "Privacy", color = Color.Gray)
                            }, onclick = { Log.i("Jesus", "-> Has pulsado Politica") }
                        )

                        ChildDrawerConfig(
                            icon = {
                                Icon(
                                    painterResource(id = R.drawable.ic_round_help_24),
                                    tint = Color.Gray,
                                    contentDescription = "help icon"
                                )
                            },
                            text = {
                                Text(text = "Help", color = Color.Gray)
                            }, onclick = { Log.i("Jesus", "-> Has pulsado help") }
                        )

                        ChildDrawerConfig(
                            icon = {
                                Icon(
                                    painterResource(id = R.drawable.ic_round_logout_24),
                                    tint = Color.Gray,
                                    contentDescription = "logout icon"
                                )
                            },
                            text = {
                                Text(text = "Exit", color = Color.Gray)
                            }, onclick = { Log.i("Jesus", "-> Has pulsado exit") }
                        )
                    }
                }
            }
        },
    ) {

        Scaffold(
            topBar = {
                TopBarView(drawerState, scope)
            },
            content = { padding ->
                CharacterList(characters, padding)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarView(drawerState: DrawerState, scope: CoroutineScope) {

    TopAppBar(
        modifier = Modifier
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
                .width(48.dp)
                .background(Color.White), contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(id = R.drawable.icon_bola_drac),
                contentDescription = "dragon ball icon",
                contentScale = ContentScale.Crop
            )
        }

    }
}

@Composable
fun ConfigMenu() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        ItemDropDrawMenu()
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
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
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
                        .wrapContentSize(),
                    text = { Text(text = s, fontSize = 16.sp, fontWeight = FontWeight.Normal) },
                    onClick = {
                        selectedIndex = index
                        expanded = false
                    })
            }
        }
    }
}


@Composable
fun ChildDrawerConfig(
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    onclick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(top = Dimensions.padding_24dp)
            .clickable { onclick() },

        verticalAlignment = Alignment.CenterVertically
    ) {
        icon()
        Spacer(modifier = Modifier.width(20.dp))
        text()
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = "arrow", tint = Color.Gray
        )
    }
}


@Composable
fun CharacterList(characters: LazyPagingItems<Character>, padding: PaddingValues) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LazyVerticalGrid(
            modifier = Modifier
                .consumeWindowInsets(padding)
                .padding(top = 20.dp), columns = GridCells.Fixed(2),
            contentPadding = padding
        ) {
            items(characters.itemCount) {
                characters[it]?.let {character->
                    ItemList(characterModel = character)
                }

            }
        }
    }
}

@Composable
fun ItemList(characterModel: Character) {
    Box(
        modifier = Modifier.padding(8.dp)
            .clip(RoundedCornerShape(24))
            .border(2.dp, Color.Yellow, shape = RoundedCornerShape(0, 24, 0, 24))
            .size(300.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = characterModel.image,
            contentDescription = "character image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(alpha = 0.0f),
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 1f)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(text = characterModel.name!!, color = Color.White, fontSize = 16.sp)
        }
    }
}









