package com.manojrai.androidgithubexplorer.ui.contributor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.manojrai.androidgithubexplorer.R
import com.manojrai.androidgithubexplorer.utils.toast
import kotlinx.android.synthetic.main.activity_contributor.*
import kotlinx.android.synthetic.main.activity_contributor.progressBar
import org.koin.android.ext.android.inject
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class ContributorsActivity : AppCompatActivity() {

    private val mViewModel: ContributorsViewModel by viewModel()

    private val contributorsAdapter: ContributorsAdapter by inject()

    private val gridLayoutManager: GridLayoutManager by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contributor)
        setUpView()
        setUpObserver()
    }

    private fun setUpView() {
        val fullName = intent.getStringExtra("fullName") ?: return
        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val avatarUrl = intent.getStringExtra("avatarUrl")
        mViewModel.getContributors(fullName)
        tvName.text = name
        tvFullName.text = fullName
        tvDescriptions.text = description
        Glide.with(ivOwner.context)
            .load(avatarUrl).into(ivOwner)

        rvContributors.apply {
            layoutManager = gridLayoutManager
            adapter = contributorsAdapter
        }

    }

    private fun setUpObserver() {
        mViewModel.messageString.observe(this, Observer {
            toast(it)
        })

        mViewModel.loading.observe(this, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        mViewModel.data.observe(this, Observer {
            contributorsAdapter.appendData(it)
        })

    }
}