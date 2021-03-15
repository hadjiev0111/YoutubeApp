package com.amir.youtubeapp.ui.activities.play

import android.content.Intent
import android.os.Bundle
import com.amir.youtubeapp.R
import com.amir.youtubeapp.`object`.Constants.API_KEY
import com.amir.youtubeapp.utils.showToast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_play.*

const val RECOVERY_REQUEST = 1

class PlayActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    var videoID: String? = null
    var title: String? = null
    var description: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        player.initialize(API_KEY, this)
        videoID = intent.getStringExtra("videoID")
        title = intent.getStringExtra("title")
        description = intent.getStringExtra("description")
        setViews()
    }

    private fun setViews() {
        playing_video_title.text = title
        playing_video_description.text = description
        play_back.setOnClickListener {
            finish()
        }
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        if (!wasRestored) {
            player?.cueVideo(videoID)
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        errorReason: YouTubeInitializationResult?
    ) {
        if (errorReason!!.isUserRecoverableError) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show()
        } else {
            val error = String.format(getString(R.string.player_error))
            this.showToast(error)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RECOVERY_REQUEST)
            player.initialize(API_KEY, this)
    }
}