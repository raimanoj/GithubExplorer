package com.manojrai.androidgithubexplorer.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.manojrai.androidgithubexplorer.R
import com.manojrai.androidgithubexplorer.utils.toast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    private val mViewModel: MainViewModel by viewModel()

    private val repositoriesAdapter: RepositoriesAdapter by inject()

    private val linearLayoutManager: LinearLayoutManager by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
        setUpObserver()
    }

    private fun setUpView() {

        rvRepo.apply {
            layoutManager = linearLayoutManager
            adapter = repositoriesAdapter
        }
    }

    private fun setUpObserver() {
        mViewModel.data.observe(this, Observer {
            repositoriesAdapter.appendData(it)
        })

        mViewModel.loading.observe(this, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        mViewModel.messageString.observe(this, Observer {
            toast(it)
        })
    }
}