package com.amir.youtubeapp.ui.activities.disconnect

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amir.youtubeapp.R
import com.amir.youtubeapp.ui.activities.playlists.PlaylistsActivity
import com.amir.youtubeapp.utils.isConnected
import kotlinx.android.synthetic.main.activity_disconnect.*

class DisconnectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disconnect)
        try_again_button.setOnClickListener {
            if (this.isConnected()) {
                val intent = Intent(this, PlaylistsActivity::class.java)
                startActivity(intent)
                finish()
            } else return@setOnClickListener
        }
    }
}