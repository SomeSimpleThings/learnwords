package com.somethingsimple.dynamichistory.di

import com.somethingsimple.dynamichistory.HistoryInteractor
import com.somethingsimple.dynamichistory.HistoryViewModel
import com.somethingsimple.dynamichistory.ui.HistoryFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal const val NAME_LOCAL = "Local"


fun injectDependencies() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(listOf(historyScreen))
}

val historyScreen = module {
    fragment { HistoryFragment() }
    factory { HistoryInteractor(get(named(NAME_LOCAL))) }
    viewModel { HistoryViewModel(get()) }

}
