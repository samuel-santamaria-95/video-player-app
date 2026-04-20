package com.example.videoplayerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.videoplayerapp.R
import com.example.videoplayerapp.model.Video

/**
 * Adapter para mostrar la lista de videos en el RecyclerView
 */
class VideoAdapter(
    private val onVideoClick: (Video) -> Unit
) : ListAdapter<Video, VideoAdapter.VideoViewHolder>(VideoDiffCallback()) {

    private var currentPlayingId: String? = null

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val thumbnail: ImageView = itemView.findViewById(R.id.videoThumbnail)
        private val title: TextView = itemView.findViewById(R.id.itemVideoTitle)
        private val description: TextView = itemView.findViewById(R.id.itemVideoDescription)
        private val playIndicator: ImageView = itemView.findViewById(R.id.playIndicator)

        fun bind(video: Video, isPlaying: Boolean) {
            title.text = video.title
            description.text = video.description

            // Cargar thumbnail con Coil
            thumbnail.load(video.thumbnailUrl) {
                crossfade(true)
                placeholder(android.R.color.black)
                error(android.R.color.black)
            }

            // Mostrar indicador de reproducción
            playIndicator.visibility = if (isPlaying) View.VISIBLE else View.GONE

            // Click listener
            itemView.setOnClickListener {
                onVideoClick(video)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = getItem(position)
        holder.bind(video, video.id == currentPlayingId)
    }

    /**
     * Actualiza el ID del video que se está reproduciendo actualmente
     */
    fun setCurrentPlaying(videoId: String?) {
        val oldId = currentPlayingId
        currentPlayingId = videoId

        // Notificar cambios solo en los items afectados
        currentList.forEachIndexed { index, video ->
            if (video.id == oldId || video.id == videoId) {
                notifyItemChanged(index)
            }
        }
    }
}

/**
 * DiffUtil para optimizar las actualizaciones del RecyclerView
 */
class VideoDiffCallback : DiffUtil.ItemCallback<Video>() {
    override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem == newItem
    }
}