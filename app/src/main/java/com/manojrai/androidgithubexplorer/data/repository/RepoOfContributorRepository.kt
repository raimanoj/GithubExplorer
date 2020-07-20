package com.manojrai.androidgithubexplorer.data.repository

import com.manojrai.androidgithubexplorer.data.remote.NetworkService

class RepoOfContributorRepository(
    private val networkService: NetworkService
) {

    fun getRepositories(url: String) = networkService.repoOfContributorCall(url)
}