package com.example.challengeflexttech.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengeflexttech.databinding.RowLoadingBinding
import com.example.challengeflexttech.model.Cat

class CategoriesAdapter(
    private var catList: List<Cat>, val itemCLick: onClick
) : RecyclerView.Adapter<BaseViewHolder<*>>() {


    interface onClick {
        fun onItemSelected(item: Cat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBanding = RowLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val take = HomeHolder(itemBanding, parent.context)
        itemBanding.root.setOnClickListener {
            val positon = take.adapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION
            }
                ?: return@setOnClickListener
           itemCLick.onItemSelected(catList[positon])


        }
        return take
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is HomeHolder -> holder.bind(catList[position])
        }
    }

    override fun getItemCount(): Int {
       return  catList.size
    }

    inner class HomeHolder(val itemPost: RowLoadingBinding, val context: Context) :
        BaseViewHolder<Cat>(itemPost.root) {
        override fun bind(item: Cat) {
            Glide.with(context).load(item.url).centerCrop().into(itemPost.rpatImMainPicture)
            itemPost.rpatTxName.text=item.id
        }
    }
    fun filterCat(catList:List<Cat>){
        this.catList=catList
        notifyDataSetChanged()
    }
}