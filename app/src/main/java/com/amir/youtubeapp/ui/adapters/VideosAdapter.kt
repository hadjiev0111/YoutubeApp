package com.amir.youtubeapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amir.youtubeapp.R
import com.amir.youtubeapp.data.model.PlaylistItem
import com.amir.youtubeapp.data.model.VideoInfo
import com.amir.youtubeapp.ui.listener.OnItemClickListener
import com.amir.youtubeapp.utils.setImageFromUrl
import kotlinx.android.synthetic.main.video_item.view.*

class VideosAdapter(var listener: OnItemClickListener) : RecyclerView.Adapter<BaseViewHolder>() {
    var items = mutableListOf<PlaylistItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        return VideosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun getItem(position: Int): PlaylistItem {
        return items[position]
    }

    fun addItems(list: MutableList<PlaylistItem>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class VideosViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun onBind(position: Int) {
            itemView.video_title.text = items[position].snippet?.title
            itemView.iv_video.setImageFromUrl(items[position].snippet?.thumbnails?.medium?.url)
            itemView.video_description.text = items[position].snippet?.description
            itemView.setOnClickListener { listener.onItemClick(adapterPosition) }
        }
    }
}