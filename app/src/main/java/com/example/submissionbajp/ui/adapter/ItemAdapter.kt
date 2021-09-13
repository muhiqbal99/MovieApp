package com.example.submissionbajp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissionbajp.R
import com.example.submissionbajp.data.source.local.entity.ItemsEntity
import com.example.submissionbajp.databinding.ItemListBinding
import com.example.submissionbajp.utils.Constants.Companion.BASE_IMG

class ItemAdapter :
    RecyclerView.Adapter<ItemAdapter.MovieMyViewHolder>() {

    private var mData: List<ItemsEntity> = emptyList()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ItemsEntity)
    }

    fun setData(items: List<ItemsEntity>?) {
        if (items == null) return
        this.mData = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieMyViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MovieMyViewHolder(mView)
    }

    override fun onBindViewHolder(holder: MovieMyViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class MovieMyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding = ItemListBinding.bind(itemView)
        fun bind(itemsEntityItem: ItemsEntity) {
            with(binding) {
                Glide.with(itemView)
                    .load(BASE_IMG + itemsEntityItem.poster)
                    .into(imgLogo)

                tvTitle.text = itemsEntityItem.title
                tvRating.text = itemsEntityItem.score.toString()
                root.setOnClickListener {
                    onItemClickCallback?.onItemClicked(itemsEntityItem)
                }
            }
        }
    }

}
