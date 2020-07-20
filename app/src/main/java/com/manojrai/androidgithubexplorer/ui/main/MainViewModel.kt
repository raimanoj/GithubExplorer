package com.manojrai.androidgithubexplorer.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manojrai.androidgithubexplorer.data.model.Repositories
import com.manojrai.androidgithubexplorer.data.repository.MainRepository
import com.manojrai.androidgithubexplorer.utils.rx.RxSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    private val schedulerProvider: RxSchedulerProvider,
    private val compositeDisposable: CompositeDisposable,
    private val mainRepository: MainRepository
) : ViewModel() {

    val messageString = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val data = MutableLiveData<List<Repositories>>()

    init {
        getRepositories()
    }

   private fun getRepositories() {
        loading.postValue(true)
        compositeDisposable.add(
            mainRepository.getRepoCall()
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    val repoList = ArrayList<Repositories>()
                    if (it.size > 20) {
                        for (i in 0 until 20) {
                            repoList.add(it[i])
                        }
                        data.postValue(repoList)
                    } else {
                        data.postValue(it)
                    }

                    loading.postValue(false)

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