package com.manojrai.androidgithubexplorer.ui.contributor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manojrai.androidgithubexplorer.data.model.Contributors
import com.manojrai.androidgithubexplorer.data.remote.Networking
import com.manojrai.androidgithubexplorer.data.repository.ContributorsRepository
import com.manojrai.androidgithubexplorer.utils.rx.RxSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ContributorsViewModel(
    private val schedulerProvider: RxSchedulerProvider,
    private val compositeDisposable: CompositeDisposable,
    private val contributorsRepository: ContributorsRepository
) : ViewModel() {
    val messageString = MutableLiveData<String>()

    val data = MutableLiveData<List<Contributors>>()

    val loading = MutableLiveData<Boolean>()

    fun getContributors(fullName: String) {
        loading.postValue(true)
        compositeDisposable.add(
            contributorsRepository.getContributors(Networking.BASE_URL + "repos/" + fullName + "/contributors")
                .subscribeOn(schedulerProvider.io())
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