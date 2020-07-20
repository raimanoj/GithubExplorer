package com.manojrai.androidgithubexplorer.data.repository

import com.manojrai.androidgithubexplorer.data.remote.NetworkService

class MainRepository(
    private val networkService: NetworkService
) {

    fun getRepoCall() = networkService.repositoriesCall()
}