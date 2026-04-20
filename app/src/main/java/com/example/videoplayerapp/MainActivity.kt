package com.example.videoplayerapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoplayerapp.adapter.VideoAdapter
import com.example.videoplayerapp.databinding.ActivityMainBinding
import com.example.videoplayerapp.model.Video
import com.example.videoplayerapp.model.VideoProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var player: ExoPlayer? = null
    private lateinit var videoAdapter: VideoAdapter
    private var currentVideo: Video? = null
    private val videos = VideoProvider.getSampleVideos()

    companion object {
        private const val TAG = "MainActivity"
        private const val KEY_CURRENT_VIDEO_ID = "current_video_id"
        private const val KEY_PLAYBACK_POSITION = "playback_position"
        private const val KEY_PLAY_WHEN_READY = "play_when_ready"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)  // ← ESTA LÍNEA ES CRÍTICA

        setupRecyclerView()

        // Restaurar estado si existe
        if (savedInstanceState != null) {
            val videoId = savedInstanceState.getString(KEY_CURRENT_VIDEO_ID)
            currentVideo = videos.find { it.id == videoId }
        }

        // Si no hay video seleccionado, reproducir el primero
        if (currentVideo == null && videos.isNotEmpty()) {
            currentVideo = videos[0]
        }
    }

    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        player?.let {
            outState.putString(KEY_CURRENT_VIDEO_ID, currentVideo?.id)
            outState.putLong(KEY_PLAYBACK_POSITION, it.currentPosition)
            outState.putBoolean(KEY_PLAY_WHEN_READY, it.playWhenReady)
        }
    }

    private fun setupRecyclerView() {
        videoAdapter = VideoAdapter { video ->
            onVideoSelected(video)
        }

        binding.playlistRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = videoAdapter
        }

        videoAdapter.submitList(videos)
    }

    private fun initializePlayer() {
        if (player == null) {
            player = ExoPlayer.Builder(this)
                .build()
                .also { exoPlayer ->
                    binding.playerView.player = exoPlayer

                    exoPlayer.addListener(object : Player.Listener {
                        override fun onPlaybackStateChanged(playbackState: Int) {
                            updateLoadingState(playbackState)
                        }

                        override fun onPlayerError(error: androidx.media3.common.PlaybackException) {
                            Log.e(TAG, "Player error: ${error.message}")
                        }

                        override fun onIsPlayingChanged(isPlaying: Boolean) {
                            Log.d(TAG, "Is playing: $isPlaying")
                        }
                    })

                    currentVideo?.let { video ->
                        playVideo(video, exoPlayer)
                    }
                }
        }
    }

    private fun releasePlayer() {
        player?.let { exoPlayer ->
            exoPlayer.release()
        }
        player = null
    }

    private fun playVideo(video: Video, exoPlayer: ExoPlayer) {
        val mediaItem = MediaItem.fromUri(video.url)

        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true

        updateVideoInfo(video)
        videoAdapter.setCurrentPlaying(video.id)
    }

    private fun onVideoSelected(video: Video) {
        if (video.id == currentVideo?.id) {
            player?.let {
                it.playWhenReady = !it.playWhenReady
            }
        } else {
            currentVideo = video
            player?.let { exoPlayer ->
                playVideo(video, exoPlayer)
            }
        }
    }

    private fun updateVideoInfo(video: Video) {
        binding.videoTitle.text = video.title
        binding.videoDescription.text = video.description
    }

    private fun updateLoadingState(playbackState: Int) {
        binding.loadingIndicator.visibility = when (playbackState) {
            Player.STATE_BUFFERING -> View.VISIBLE
            Player.STATE_READY, Player.STATE_ENDED -> View.GONE
            else -> View.GONE
        }
    }
}