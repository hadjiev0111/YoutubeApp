package com.amir.youtubeapp.ui.activities.playlists

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amir.youtubeapp.R
import com.amir.youtubeapp.ui.activities.disconnect.DisconnectActivity
import com.amir.youtubeapp.ui.activities.videos.VideosActivity
import com.amir.youtubeapp.ui.activities.playlists.adapter.PlaylistsAdapter
import com.amir.youtubeapp.ui.listener.OnItemClickListener
import com.amir.youtubeapp.utils.isConnected
import kotlinx.android.synthetic.main.activity_playlists.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistsActivity : AppCompatActivity(), OnItemClickListener {

    private val model: PlaylistsViewModel by viewModel()

    private lateinit var adapter: PlaylistsAdapter

    private var layoutManager: LinearLayoutManager = LinearLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlists)
        if (!this.isConnected()) {
            val intent = Intent(this, DisconnectActivity::class.java)
            startActivity(intent)
            finish()
        }
        initAdapter()
        model.setPlaylists().observe(this, Observer {
            adapter.addItems(it)
        })
    }

    private fun initAdapter() {
        adapter = PlaylistsAdapter(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        playlists_rv.layoutManager = layoutManager
        playlists_rv.itemAnimator = DefaultItemAnimator()
        playlists_rv.adapter = adapter
        playlists_rv.isNestedScrollingEnabled = true
    }

    override fun onItemClick(pos: Int) {
        var intent = Intent(this, VideosActivity::class.java)
        intent.putExtra("playlistId", adapter.getItem(pos).id)
        intent.putExtra("title", adapter.getItem(pos).snippet?.title)
        intent.putExtra("description", adapter.getItem(pos).snippet?.description)
        intent.putExtra("video_count", adapter.getItem(pos).contentDetails?.itemCount)
        startActivity(intent)
    }
}