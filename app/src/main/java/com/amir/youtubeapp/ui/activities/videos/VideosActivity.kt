package com.amir.youtubeapp.ui.activities.videos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amir.youtubeapp.R
import com.amir.youtubeapp.ui.activities.play.PlayActivity
import com.amir.youtubeapp.ui.activities.disconnect.DisconnectActivity
import com.amir.youtubeapp.ui.adapters.VideosAdapter
import com.amir.youtubeapp.ui.listener.OnItemClickListener
import com.amir.youtubeapp.utils.isConnected
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_videos.*
import kotlinx.android.synthetic.main.content_scrolling.*

class VideosActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var model: VideosViewModel
    private lateinit var adapter: VideosAdapter
    private var layoutManager: LinearLayoutManager = LinearLayoutManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videos)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        if (!this.isConnected()) {
            val intent = Intent(this, DisconnectActivity::class.java)
            startActivity(intent)
            finish()
        }
        model = ViewModelProvider(this)[VideosViewModel::class.java]
        modelActions(model)

    }

    private fun modelActions(model: VideosViewModel) {
        initAdapter()
        var id = intent.getStringExtra("playlistId")
        title = intent.getStringExtra("title")
        var description = intent.getStringExtra("description")
        var count = intent.getStringExtra("videos_count")
        playlist_title.text = title
        playlist_description.text = description
        videos_count.text = count
        model.setVideos(id ?: "")
        model.videos.observe(this, Observer {
            adapter.addItems(it)
        })
        playlist_back.setOnClickListener {
            finish()
        }
    }

    private fun initAdapter() {
        adapter = VideosAdapter(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        rv_videos.layoutManager = layoutManager
        rv_videos.itemAnimator = DefaultItemAnimator()
        rv_videos.adapter = adapter
        rv_videos.isNestedScrollingEnabled = true
    }

    override fun onItemClick(pos: Int) {
        val i = Intent(this, PlayActivity::class.java)
        i.putExtra("videoID", adapter.getItem(pos).contentDetails?.videoId)
        i.putExtra("title", adapter.getItem(pos).snippet?.title)
        i.putExtra("description", adapter.getItem(pos).snippet?.description)
        startActivity(i)
    }
}