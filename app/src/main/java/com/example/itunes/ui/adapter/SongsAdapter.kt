package com.example.itunes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.itunes.R
import com.example.itunes.data.model.Result

class SongsAdapter(
    private val context: Context
): ListAdapter<Result, SongsAdapter.SongsHolder>(DIFF_ITEM_CALLBACK) {

    companion object {
        val DIFF_ITEM_CALLBACK = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.artistId == newItem.artistId
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsHolder {
        val v: View = LayoutInflater.from(context).inflate(R.layout.custom_item, parent, false)
        return SongsHolder(v)
    }

    override fun onBindViewHolder(holder: SongsHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SongsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val trackName: AppCompatTextView = itemView.findViewById(R.id.tv_trackName)
        private val collection: AppCompatTextView = itemView.findViewById(R.id.tv_collectionName)
        private val artistName: AppCompatTextView = itemView.findViewById(R.id.tv_artistName)
        private val image: AppCompatImageView = itemView.findViewById(R.id.image)

        fun bind(result: Result) {
            trackName.text = result.trackName
            collection.text = result.collectionName
            artistName.text = result.artistName

            Glide.with(itemView.context)
                .load(result.artworkUrl100)
                .into(image)

        }

    }

}