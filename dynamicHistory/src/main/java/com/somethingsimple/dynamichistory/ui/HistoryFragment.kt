package com.somethingsimple.dynamichistory.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.somethingsimple.core.BaseFragment
import com.somethingsimple.dynamichistory.HistoryViewModel
import com.somethingsimple.dynamichistory.databinding.FragmentHistoryBinding
import com.somethingsimple.dynamichistory.di.injectDependencies
import com.somethingsimple.model.WordlistState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseFragment<WordlistState>() {
    var binding: FragmentHistoryBinding? = null
    private var historyAdapter: HistoryAdapter? = null
    lateinit var model: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iniViewModel()
    }

    private fun iniViewModel() {
        injectDependencies()
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentHistoryBinding.inflate(
        LayoutInflater.from(requireContext()),
        container,
        false
    ).apply {
        binding = this
    }.root

    companion object {
        @JvmStatic
        fun newInstance() =
            HistoryFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (historyAdapter == null) {
            historyAdapter = HistoryAdapter()
            binding?.recyclerview?.apply {
                layoutManager =
                    LinearLayoutManager(requireContext())
                adapter = historyAdapter
            }
        }
        model.getData("", false)
        model.subscribe().observe(viewLifecycleOwner, { renderData(it) })

    }

    override fun renderData(appState: WordlistState) {
        when (appState) {
            is WordlistState.Success -> {
                appState.data?.let { historyAdapter!!.setData(it) }
            }

            is WordlistState.Error -> {
            }

            is WordlistState.Loading -> {

            }
        }
    }

}