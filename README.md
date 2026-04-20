# 📱 Video Player App - Android Learning Project

> A modern video player application built with Kotlin and ExoPlayer as part of my Android development learning journey.

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.20-purple.svg)](https://kotlinlang.org)
[![MinSDK](https://img.shields.io/badge/MinSDK-24-green.svg)](https://developer.android.com/about/versions/nougat)
[![TargetSDK](https://img.shields.io/badge/TargetSDK-34-blue.svg)](https://developer.android.com)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 🎯 Project Overview

This is the **Video Player App** about Android learning roadmap. The goal is to master the fundamentals of Android video playback, RecyclerView implementation, and proper lifecycle management.

## 📸 Screenshots

<img src="screenshots/player_screen.png" width="250" alt="Player Screen"> <img src="screenshots/playlist.png" width="250" alt="Playlist">

*Note: Screenshots coming soon*

## ✨ Features

- ✅ **Video Playback** - Stream videos from remote URLs
- ✅ **Playlist Management** - Multiple videos with seamless switching
- ✅ **Built-in Controls** - Play, pause, seek, and fullscreen support
- ✅ **Visual Indicator** - Shows currently playing video in the list
- ✅ **HLS Streaming** - Support for adaptive bitrate streaming
- ✅ **Lifecycle-Aware** - Proper player resource management
- ✅ **Material Design** - Modern UI following Material Design guidelines

## 🛠️ Tech Stack

### Core Technologies
- **Language:** Kotlin
- **Min SDK:** 24 (Android 7.0 Nougat)
- **Target SDK:** 34 (Android 14)
- **Architecture:** MVC (planned migration to MVVM)

### Libraries & Dependencies

| Library | Version | Purpose |
|---------|---------|---------|
| [ExoPlayer (Media3)](https://developer.android.com/guide/topics/media/exoplayer) | 1.2.1 | Video playback engine |
| [media3-exoplayer-hls](https://developer.android.com/guide/topics/media/media3) | 1.2.1 | HLS streaming support |
| [Coil](https://coil-kt.github.io/coil/) | 2.5.0 | Image loading library |
| [Material Components](https://material.io/develop/android) | 1.11.0 | Material Design UI components |
| [ViewBinding](https://developer.android.com/topic/libraries/view-binding) | Built-in | Type-safe view access |

## 📁 Project Structure

app/src/main/
├── kotlin/com/example/videoplayerapp/
│   ├── MainActivity.kt              # Main activity controller
│   ├── adapter/
│   │   └── VideoAdapter.kt          # RecyclerView adapter
│   └── model/
│       └── Video.kt                 # Data model & provider
└── res/
├── layout/
│   ├── activity_main.xml        # Main screen layout
│   └── item_video.xml           # Video list item layout
└── values/
├── colors.xml               # Color palette
├── strings.xml              # String resources
└── themes.xml               # App themes

## 🏗️ Architecture

┌─────────────────────────────────────┐
│  UI Layer (Presentation)            │
│  - MainActivity.kt                  │
│  - activity_main.xml                │
│  - item_video.xml                   │
└─────────────┬───────────────────────┘
│
▼
┌─────────────────────────────────────┐
│  Adapter Layer                      │
│  - VideoAdapter.kt                  │
│  - ViewHolder pattern               │
└─────────────┬───────────────────────┘
│
▼
┌─────────────────────────────────────┐
│  Player Layer                       │
│  - ExoPlayer (Media3)               │
│  - Lifecycle management             │
└─────────────┬───────────────────────┘
│
▼
┌─────────────────────────────────────┐
│  Data Layer                         │
│  - Video.kt (model)                 │
│  - VideoProvider (data source)      │
└─────────────────────────────────────┘

## 🚀 Getting Started

### Prerequisites

- **Android Studio:** Hedgehog (2023.1.1) or newer
- **JDK:** Version 8 or higher
- **Gradle:** 8.2.0 or higher
- **Internet connection** (required for video streaming)

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/YOUR_USERNAME/video-player-app.git
cd video-player-app
```

2. **Open in Android Studio**
File → Open → Select the project folder

3. **Sync Gradle**
File → Sync Project with Gradle Files

4. **Run the app**
- Connect a device or start an emulator
- Click the Run button (▶️) or press `Shift + F10`

### Configuration

**Update video sources** (optional):

Edit `app/src/main/kotlin/.../model/Video.kt`:

```kotlin
object VideoProvider {
    fun getSampleVideos(): List<Video> {
        return listOf(
            Video(
                id = "1",
                title = "Your Video Title",
                description = "Description here",
                url = "https://your-video-url.mp4",
                thumbnailUrl = "https://thumbnail-url.jpg"
            )
            // Add more videos...
        )
    }
}
```

## 📚 Key Learnings

### Concepts Implemented

| Concept | Implementation |
|---------|----------------|
| **ViewBinding** | Type-safe view access replacing findViewById |
| **RecyclerView Pattern** | ViewHolder + Adapter + DiffUtil |
| **ExoPlayer Lifecycle** | Created in onStart(), released in onStop() |
| **Lambda Expressions** | Simplified callback syntax in Kotlin |
| **Data Classes** | Immutable data modeling |
| **Material Design** | CardView, ConstraintLayout, themes |

### Challenges Overcome

✅ **ExoPlayer Configuration** - Successfully integrated Media3 ExoPlayer  
✅ **Lifecycle Management** - Proper player creation/release to prevent memory leaks  
✅ **HLS Support** - Added media3-exoplayer-hls dependency for .m3u8 files  
✅ **403 Errors** - Resolved Google Cloud Storage blocking issues  
✅ **Theme Compatibility** - Fixed AppCompat vs Material3 theme conflicts  

## 🎓 Development Journey

This project is **part 1 of 5** in my Android learning roadmap:

1. ✅ **Video Player Basics** (This project)
2. ⏳ **Streaming App** (HLS/DASH + Adaptive Bitrate)
3. ⏳ **DRM Protected Player** (Widevine implementation)
4. ⏳ **Live TV App** (EPG + Timeshift + Catchup)
5. ⏳ **OTT Platform Clone** (Full-featured streaming platform)

### What's Next?

The next project will introduce:
- HLS and DASH protocols
- Manual quality selection (360p, 720p, 1080p)
- Real-time playback statistics
- MVVM architecture with ViewModel
- Repository pattern
- Kotlin Coroutines for async operations

## 🔄 Roadmap & Future Improvements

- [ ] **Architecture:** Migrate to MVVM with ViewModel
- [ ] **Data:** Implement Repository pattern
- [ ] **Persistence:** Add local database with Room
- [ ] **Features:**
  - [ ] Manual quality selector
  - [ ] DASH streaming support
  - [ ] Picture-in-Picture (PiP) mode
  - [ ] Persistent playlist
  - [ ] Download for offline viewing
  - [ ] Playback speed controls
- [ ] **Testing:** Add unit tests and UI tests
- [ ] **CI/CD:** GitHub Actions for automated builds

## 🧪 Testing

*Unit tests and instrumentation tests coming in future iterations*

### Manual Testing Checklist

- [x] Video playback starts automatically
- [x] Playlist displays all videos
- [x] Tapping video in list starts playback
- [x] Play/Pause controls work correctly
- [x] Visual indicator shows current video
- [x] Player releases resources on app minimize
- [x] App survives configuration changes (rotation)

## 📖 Resources & References

### Official Documentation
- [ExoPlayer Documentation](https://developer.android.com/guide/topics/media/exoplayer)
- [Media3 Migration Guide](https://developer.android.com/guide/topics/media/media3/getting-started/migration-guide)
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Android Developer Guides](https://developer.android.com/guide)

### Learning Materials
- [Android Codelabs](https://developer.android.com/courses)
- [Material Design Guidelines](https://material.io/design)
- [Kotlin by Example](https://play.kotlinlang.org/byExample/overview)

### Inspiration
- [Google ExoPlayer Demo App](https://github.com/androidx/media/tree/release/demos)
- [Android Architecture Samples](https://github.com/android/architecture-samples)

## 🤝 Contributing

This is a learning project, but feedback is always welcome!

### How to Contribute

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Suggestions Welcome

- Code improvements and refactoring ideas
- Better architectural patterns
- Performance optimizations
- Bug reports

## 👨‍💻 Author

**Your Name**

- GitHub: [@your_username](https://github.com/your_username)
- LinkedIn: [Your Name](https://linkedin.com/in/your-profile)
- Email: your.email@example.com
- Portfolio: [yourportfolio.com](https://yourportfolio.com)

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

MIT License
Copyright (c) 2026 Your Name
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

## 🙏 Acknowledgments

- **Blender Foundation** - For providing free test videos
- **Google ExoPlayer Team** - For the excellent media playback library
- **Android Community** - For countless helpful resources and tutorials
- **Stack Overflow Community** - For troubleshooting assistance

## 📊 Project Stats

![GitHub repo size](https://img.shields.io/github/repo-size/your_username/video-player-app)
![GitHub commit activity](https://img.shields.io/github/commit-activity/m/your_username/video-player-app)
![GitHub last commit](https://img.shields.io/github/last-commit/your_username/video-player-app)

---

⭐ **If you found this project helpful, please consider giving it a star!**

💬 **Have questions?** Feel free to open an issue or reach out!

📚 **Learning Android?** Check out my other projects in the [Android Learning Series](https://github.com/your_username?tab=repositories)

---

*Built with ❤️ as part of my journey to become an Android developer*