package com.manojrai.androidgithubexplorer.ui.repoofcontributor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manojrai.androidgithubexplorer.R
import com.manojrai.androidgithubexplorer.data.model.Repositories
import kotlinx.android.synthetic.main.item_layout_repositories.view.*

class RepoOfContributorAdapter(
    private var list: List<Repositories>
) : RecyclerView.Adapter<RepoOfContributorAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val listItem =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout_repositories, parent, false)
        return MyViewHolder(listItem)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    fun appendData(dataList: List<Repositories>) {
        list = dataList
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(repositories: Repositories) {
            itemView.tvName.text = repositories.name
            itemView.tvFullName.text = repositories.fullName
            Glide.with(itemView.context)
                .load(repositories.owner.avatarUrl)
                .into(itemView.ivOwner)
        }
    }
}
