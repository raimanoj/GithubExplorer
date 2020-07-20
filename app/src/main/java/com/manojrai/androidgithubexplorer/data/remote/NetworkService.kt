package com.manojrai.androidgithubexplorer.data.remote

import com.manojrai.androidgithubexplorer.data.model.Contributors
import com.manojrai.androidgithubexplorer.data.model.Repositories
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface NetworkService {

    @GET(Endpoints.REPOSITORIES)
    fun repositoriesCall(): Single<List<Repositories>>

    @GET
    fun contributorsOfRepoCall(@Url url: String): Single<List<Contributors>>

    @GET
    fun repoOfContributorCall(@Url url: String): Single<List<Repositories>>
}