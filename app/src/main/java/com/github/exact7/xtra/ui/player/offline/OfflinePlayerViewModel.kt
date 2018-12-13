package com.github.exact7.xtra.ui.player.offline

import android.app.Application
import com.github.exact7.xtra.model.OfflineVideo
import com.github.exact7.xtra.ui.player.PlayerViewModel
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import javax.inject.Inject

class OfflinePlayerViewModel @Inject constructor(
        context: Application) : PlayerViewModel(context) {

    lateinit var video: OfflineVideo
    private var playbackProgress: Long = 0

    fun setVideo(video: OfflineVideo) {
        val mediaSourceFactory = if (video.vod) {
            HlsMediaSource.Factory(dataSourceFactory)
        } else {
            ExtractorMediaSource.Factory(dataSourceFactory)
        }
        mediaSource = mediaSourceFactory.createMediaSource(Uri.parse(video.url))
//        play()
    }

//    override fun play() {
//        super.play()
//        player.seekTo(playbackProgress)
//    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        super.onPlayerStateChanged(playWhenReady, playbackState)
        when (playbackState) {
            Player.STATE_IDLE -> playbackProgress = player.currentPosition
        }
    }
}
