package com.manojrai.androidgithubexplorer.data.repository

import com.manojrai.androidgithubexplorer.data.remote.NetworkService

class ContributorsRepository(
    private val networkService: NetworkService
) {

    fun getContributors(url: String) = networkService.contributorsOfRepoCall(url)
}