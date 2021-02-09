package com.example.musicplayerclone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.musicplayerclone.data.entities.Song
import com.example.musicplayerclone.databinding.ListItemBinding
import javax.inject.Inject


abstract class BaseSongAdapter (
    private val layoutId: Int
): RecyclerView.Adapter<BaseSongAdapter.SongViewHolder>(){

    class SongViewHolder(private val bind: ListItemBinding): RecyclerView.ViewHolder(bind.root){
        fun bind(song: Song){
            bind.tvPrimary.text = "${song.title} - ${song.subtitle}"
            bind.tvSecondary.text = song.subtitle
            Glide.with(bind.root.context).load(song.imageUrl).into(bind.ivItemImage)

        }
    }


    protected val diffCallback = object : DiffUtil.ItemCallback<Song>(){
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.mediaId == newItem.mediaId
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    protected abstract val differ: AsyncListDiffer<Song>

    var songs: List<Song>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
                ListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        )
    }

    protected var onItemClickListener: ((Song) -> Unit)? = null

    fun setItemClickListener(listener: (Song) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return songs.size
    }
}