package com.manojrai.androidgithubexplorer.ui.repoofcontributor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.manojrai.androidgithubexplorer.R
import com.manojrai.androidgithubexplorer.utils.toast
import kotlinx.android.synthetic.main.activity_repo_of_contributor.*
import kotlinx.android.synthetic.main.activity_repo_of_contributor.progressBar
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RepoOfContributorActivity : AppCompatActivity() {

    private val mViewModel: RepoOfContributorViewModel by viewModel()

    private val repoOfContributorAdapter: RepoOfContributorAdapter by inject()

    private val linearLayoutManager: LinearLayoutManager by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_of_contributor)
        setUpView()
        setUpObserver()
    }

    private fun setUpView() {
        val name = intent.getStringExtra("name") ?: return
        val avatarUrl = intent.getStringExtra("avatarUrl")
        tvName.text = name
        Glide.with(ivAvatar.context).load(avatarUrl)
            .into(ivAvatar)
        mViewModel.getRepositories(name)

        rvRepositories.apply {
            layoutManager = linearLayoutManager
            adapter = repoOfContributorAdapter
        }
    }

    private fun setUpObserver() {
        mViewModel.data.observe(this, Observer {
            repoOfContributorAdapter.appendData(it)
        })

        mViewModel.loading.observe(this, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        mViewModel.messageString.observe(this, Observer {
            toast(it)
        })
    }


}