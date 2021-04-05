package com.amir.youtubeapp.ui.activities.videos

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amir.youtubeapp.R
import com.amir.youtubeapp.ui.activities.play.PlayActivity
import com.amir.youtubeapp.ui.activities.disconnect.DisconnectActivity
import com.amir.youtubeapp.ui.activities.videos.adapter.VideosAdapter
import com.amir.youtubeapp.ui.listener.OnItemClickListener
import com.amir.youtubeapp.utils.isConnected
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_videos.*
import kotlinx.android.synthetic.main.content_scrolling.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideosActivity : AppCompatActivity(), OnItemClickListener {

    private val model: VideosViewModel by viewModel()
    private lateinit var adapter: VideosAdapter
    private var layoutManager: LinearLayoutManager = LinearLayoutManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videos)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener{ fabClick(it) }


        if (!this.isConnected()) {
            val intent = Intent(this, DisconnectActivity::class.java)
            startActivity(intent)
            finish()
        }
        modelActions(model)

    }

    private fun modelActions(model: VideosViewModel) {
        initAdapter()
        val id = intent.getStringExtra("playlistId")
        title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val count = intent.getStringExtra("videos_count")
        playlist_title.text = title
        playlist_description.text = description
        model.setVideos(id ?: "")
        model.videos.observe(this, Observer {
            adapter.addItems(it)
            val viewCountString = StringBuffer()
            viewCountString.append(adapter.items.size)
            viewCountString.append(" video items")
            videos_count.text = viewCountString

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

    fun fabClick(view: View) {
        val firstItem = adapter.items[0]

        val i = Intent(this, PlayActivity::class.java)
        i.putExtra("videoID", firstItem.contentDetails?.videoId)
        i.putExtra("title", firstItem.snippet?.title)
        i.putExtra("description", firstItem.snippet?.description)
        startActivity(i)
    }
}