package com.manojrai.androidgithubexplorer.ui.repoofcontributor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manojrai.androidgithubexplorer.data.model.Repositories
import com.manojrai.androidgithubexplorer.data.remote.Networking
import com.manojrai.androidgithubexplorer.data.repository.RepoOfContributorRepository
import com.manojrai.androidgithubexplorer.utils.rx.RxSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class RepoOfContributorViewModel(
    private val schedulerProvider: RxSchedulerProvider,
    private val compositeDisposable: CompositeDisposable,
    private val repoOfContributorRepository: RepoOfContributorRepository
) : ViewModel() {

    val messageString = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val data = MutableLiveData<List<Repositories>>()

    fun getRepositories(userName: String) {
        loading.postValue(true)
        compositeDisposable.add(
            repoOfContributorRepository.getRepositories(
                Networking.BASE_URL + "users/" + userName + "/repos"
            ).subscribeOn(schedulerProvider.io())
                .subscribe({
                    loading.postValue(false)
                    data.postValue(it)

                }, {
                    loading.postValue(false)
                    messageString.postValue(it.message)
                })
        )
    }


    override fun onCleared() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
        super.onCleared()
    }
}