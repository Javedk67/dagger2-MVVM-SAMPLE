package com.example.dagger2sample.ui.fragments

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.dagger2sample.R
import com.example.dagger2sample.data.model.MovieBean
import com.example.dagger2sample.databinding.ItemImageBinding


class PostListAdapter (var context: Context,private val list:List<MovieBean>) : PagingDataAdapter<MovieBean,PostListAdapter.ViewHolder>(
    DiffCallback()
) {

    private var movieBean: MovieBean? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ItemImageBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_image, parent, false)
        return PostListAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
            var movieBean=it
            if (!movieBean.Poster.isNullOrEmpty()) {
                Glide.with(context)
                    .load(movieBean.Poster)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.binding.imageView)
            }
            holder.binding.desc.text=movieBean.Title
            holder.binding.by.text=movieBean.Year
        }


    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.setVariable(0, data)
            binding.executePendingBindings()


        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<MovieBean>() {
        override fun areItemsTheSame(oldItem: MovieBean, newItem: MovieBean): Boolean {
            return oldItem.imdbID == newItem.imdbID
        }

        override fun areContentsTheSame(oldItem: MovieBean, newItem: MovieBean): Boolean {
            return oldItem == newItem
        }
    }


}