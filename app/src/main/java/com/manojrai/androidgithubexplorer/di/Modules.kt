package com.manojrai.androidgithubexplorer.di

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.manojrai.androidgithubexplorer.data.remote.Networking
import com.manojrai.androidgithubexplorer.data.repository.ContributorsRepository
import com.manojrai.androidgithubexplorer.data.repository.MainRepository
import com.manojrai.androidgithubexplorer.data.repository.RepoOfContributorRepository
import com.manojrai.androidgithubexplorer.ui.contributor.ContributorsActivity
import com.manojrai.androidgithubexplorer.ui.contributor.ContributorsAdapter
import com.manojrai.androidgithubexplorer.ui.contributor.ContributorsViewModel
import com.manojrai.androidgithubexplorer.ui.main.MainViewModel
import com.manojrai.androidgithubexplorer.ui.main.RepositoriesAdapter
import com.manojrai.androidgithubexplorer.ui.repoofcontributor.RepoOfContributorAdapter
import com.manojrai.androidgithubexplorer.ui.repoofcontributor.RepoOfContributorViewModel
import com.manojrai.androidgithubexplorer.utils.rx.RxSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    single {
        Networking.create(
            Networking.BASE_URL,
            androidApplication().cacheDir,
            10 * 1024 * 1024 // 10MB
        )
    }

    factory { RxSchedulerProvider() }

    factory { CompositeDisposable() }

    factory {
        LinearLayoutManager(it[0])
    }

}

val mainModule = module {

    factory { MainRepository(networkService = get()) }

    viewModel {
        MainViewModel(
            schedulerProvider = get(),
            compositeDisposable = get(),
            mainRepository = get()
        )
    }

    factory {
        RepositoriesAdapter(emptyList())
    }
}

val contributorsModule = module {

    factory { ContributorsRepository(networkService = get()) }

    viewModel {
        ContributorsViewModel(
            schedulerProvider = get(),
            compositeDisposable = get(),
            contributorsRepository = get()
        )
    }

    factory {
        ContributorsAdapter(emptyList())
    }

    scope(named<ContributorsActivity>()) {
        scoped {
            GridLayoutManager(get(),  3)
        }
    }
      /*factory {
          GridLayoutManager(it[0], it[1])
      }*/
}


val repoOfContributorModule = module {

    factory { RepoOfContributorRepository(networkService = get()) }

    viewModel {
        RepoOfContributorViewModel(
            schedulerProvider = get(),
            compositeDisposable = get(),
            repoOfContributorRepository = get()
        )
    }

    factory {
        RepoOfContributorAdapter(emptyList())
    }
}