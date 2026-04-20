package com.example.videoplayerapp.model

/**
 * Modelo de datos para un video
 * @param id Identificador único del video
 * @param title Título del video
 * @param description Descripción breve
 * @param url URL del video (puede ser local o remota)
 * @param thumbnailUrl URL de la miniatura (opcional)
 * @param duration Duración en milisegundos (opcional, se puede calcular)
 */
data class Video(
    val id: String,
    val title: String,
    val description: String,
    val url: String,
    val thumbnailUrl: String? = null,
    val duration: Long? = null
)

/**
 * Objeto que proporciona videos de ejemplo para testing
 */
object VideoProvider {

    fun getSampleVideos(): List<Video> {
        return listOf(
            Video(
                id = "1",
                title = "Big Buck Bunny",
                description = "Cortometraje animado HD",
                url = "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8",
                thumbnailUrl = null
            ),
            Video(
                id = "2",
                title = "Tears of Steel",
                description = "Demo en 4K",
                url = "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.mp4/.m3u8",
                thumbnailUrl = null
            ),
            Video(
                id = "3",
                title = "Elephant's Dream",
                description = "Primer proyecto libre de Blender",
                url = "https://archive.org/download/ElephantsDream/ed_hd.mp4",
                thumbnailUrl = null
            ),
            Video(
                id = "4",
                title = "Sintel Trailer",
                description = "Fantasía épica animada",
                url = "https://archive.org/download/Sintel/sintel-2048-surround.mp4",
                thumbnailUrl = null
            ),
            Video(
                id = "5",
                title = "Sample Video",
                description = "Video de prueba MP4",
                url = "https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_1mb.mp4",
                thumbnailUrl = null
            )
        )
    }
}