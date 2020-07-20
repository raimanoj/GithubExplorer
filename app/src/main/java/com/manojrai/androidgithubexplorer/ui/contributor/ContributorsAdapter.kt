package com.manojrai.androidgithubexplorer.ui.contributor

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manojrai.androidgithubexplorer.R
import com.manojrai.androidgithubexplorer.data.model.Contributors
import com.manojrai.androidgithubexplorer.ui.repoofcontributor.RepoOfContributorActivity
import kotlinx.android.synthetic.main.item_layout_contributors.view.*

class ContributorsAdapter(private var list: List<Contributors>) :
    RecyclerView.Adapter<ContributorsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val listItem =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout_contributors, parent, false)
        return MyViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    fun appendData(dataList: List<Contributors>) {
        list = dataList
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(contributors: Contributors) {
            itemView.tvName.text = contributors.name
            Glide.with(itemView.context).load(contributors.avatarUrl)
                .into(itemView.ivAvatar)
            itemView.setOnClickListener {
                Intent(itemView.context, RepoOfContributorActivity::class.java).apply {
                    putExtra("name", contributors.name)
                    putExtra("avatarUrl", contributors.avatarUrl)
                    itemView.context.startActivity(this)
                }
            }
        }
    }

}