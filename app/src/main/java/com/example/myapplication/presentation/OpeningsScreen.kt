package com.example.myapplication.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.AsyncImage
import com.example.myapplication.ui.theme.ColorCard
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

private val openingVideos = listOf(
    OpeningVideo(
        title = "Opening 1",
        series = "Dragon Ball",
        youtubeUrl = "https://youtu.be/L4auFrAK-mQ",
        youtubeId = "L4auFrAK-mQ",
        isEmbeddable = false
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpeningsScreen(navigateBack: () -> Unit) {
    var selectedVideo by remember { mutableStateOf(openingVideos.first()) }

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
                    Text(text = "Openings", fontWeight = FontWeight.Bold)
                }
            )
        }
    ) { padding ->
        OpeningContent(
            selectedVideo = selectedVideo,
            videos = openingVideos,
            padding = padding,
            onVideoSelected = { selectedVideo = it }
        )
    }
}

@Composable
private fun OpeningContent(
    selectedVideo: OpeningVideo,
    videos: List<OpeningVideo>,
    padding: PaddingValues,
    onVideoSelected: (OpeningVideo) -> Unit,
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(padding)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            if (selectedVideo.isEmbeddable) {
                YoutubeVideoPlayer(selectedVideo)
            } else {
                YoutubeVideoPreview(
                    video = selectedVideo,
                    onClick = { openYoutubeVideo(context, selectedVideo) }
                )
            }
        }

        item {
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { openYoutubeVideo(context, selectedVideo) }
            ) {
                Text(text = "Abrir en YouTube")
            }
        }

        item {
            Text(
                text = "Lista de openings",
                color = ColorCard,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        items(videos, key = { it.youtubeId }) { video ->
            OpeningVideoItem(
                video = video,
                isSelected = video.youtubeId == selectedVideo.youtubeId,
                onClick = { onVideoSelected(video) }
            )
        }
    }
}

@Composable
private fun OpeningVideoItem(
    video: OpeningVideo,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val borderColor = if (isSelected) ColorCard else Color.Transparent

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, borderColor, RoundedCornerShape(8.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F5FA))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = video.title,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = video.series,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun YoutubeVideoPreview(
    video: OpeningVideo,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Black)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = video.thumbnailUrl,
            contentDescription = video.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.32f))
        )
        Icon(
            modifier = Modifier
                .background(Color.White.copy(alpha = 0.9f), RoundedCornerShape(32.dp))
                .padding(14.dp),
            imageVector = Icons.Rounded.PlayArrow,
            contentDescription = "Abrir video",
            tint = ColorCard
        )
    }
}

@Composable
private fun YoutubeVideoPlayer(video: OpeningVideo) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val currentVideo by rememberUpdatedState(video)
    var youtubePlayer by remember { mutableStateOf<YouTubePlayer?>(null) }

    val youtubePlayerView = remember(context) {
        YouTubePlayerView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(android.graphics.Color.BLACK)
            enableAutomaticInitialization = false

            val listener = object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youtubePlayer = youTubePlayer
                    tag = currentVideo.youtubeId
                    youTubePlayer.cueVideo(currentVideo.youtubeId, 0f)
                }
            }
            val options = IFramePlayerOptions.Builder()
                .controls(1)
                .rel(0)
                .fullscreen(1)
                .build()

            initialize(listener, true, options)
        }
    }

    DisposableEffect(lifecycleOwner, youtubePlayerView) {
        lifecycleOwner.lifecycle.addObserver(youtubePlayerView)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(youtubePlayerView)
            youtubePlayerView.release()
        }
    }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .clip(RoundedCornerShape(8.dp)),
        factory = { youtubePlayerView },
        update = { playerView ->
            if (playerView.tag != video.youtubeId) {
                playerView.tag = video.youtubeId
                youtubePlayer?.cueVideo(video.youtubeId, 0f)
            }
        }
    )
}

private fun openYoutubeVideo(context: Context, video: OpeningVideo) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(video.youtubeUrl))
    context.startActivity(intent)
}

private data class OpeningVideo(
    val title: String,
    val series: String,
    val youtubeUrl: String,
    val youtubeId: String,
    val isEmbeddable: Boolean = true,
) {
    val thumbnailUrl: String = "https://img.youtube.com/vi/$youtubeId/hqdefault.jpg"
}
