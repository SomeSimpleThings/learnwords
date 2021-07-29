package com.somethingsimple.learnwords.ui.words.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.somethingsimple.learnwords.R
import com.somethingsimple.learnwords.data.WordlistState
import com.somethingsimple.learnwords.data.vo.Word
import com.somethingsimple.learnwords.databinding.FragmentWordlistBinding
import com.somethingsimple.learnwords.presenter.Presenter
import com.somethingsimple.learnwords.ui.base.BaseFragment
import com.somethingsimple.learnwords.ui.words.WordsPresenter


class WordlistFragment : BaseFragment<WordlistState>() {

    private var wordAdapter: WordAdapter? = null
    var binding: FragmentWordlistBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentWordlistBinding.inflate(
        LayoutInflater.from(requireContext()),
        container,
        false
    ).apply {
        binding = this
        binding.apply {
            searchButton.setOnClickListener {
                editTextSearch.text.toString()
                presenter.getData(editTextSearch.text.toString(), true)
            }
        }
    }.root


    companion object {

        @JvmStatic
        fun newInstance() =
            WordlistFragment()

    }

    override fun createPresenter()
            : Presenter<WordlistState, com.somethingsimple.learnwords.ui.base.View> =
        WordsPresenter()

    override fun renderData(appState: WordlistState) {
        when (appState) {
            is WordlistState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (wordAdapter == null) {
                        wordAdapter = WordAdapter(
                            object : WordAdapter.OnListItemClickListener {
                                override fun onItemClick(data: Word) {
                                    Toast.makeText(requireContext(), data.text, Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }, dataModel
                        )
                        binding?.mainActivityRecyclerview?.apply {
                            layoutManager =
                                LinearLayoutManager(requireContext())
                            adapter = wordAdapter
                        }
                    } else {
                        wordAdapter!!.setData(dataModel)
                    }
                }
            }
            is WordlistState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding?.apply {
                        progressBarHorizontal.visibility = View.VISIBLE
                        progressBarRound.visibility = View.GONE
                        progressBarHorizontal.progress = appState.progress
                    }
                } else {
                    binding?.apply {
                        progressBarHorizontal.visibility = View.GONE
                        progressBarRound.visibility = View.VISIBLE
                    }

                }
            }
            is WordlistState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding?.apply {
            errorTextview.text = error ?: getString(R.string.undefined_error)
            reloadButton.setOnClickListener {
            }

            presenter.getData("hi", true)
        }
    }

    private fun showViewSuccess() {
        binding?.apply {
            successLinearLayout.visibility = View.VISIBLE
            loadingFrameLayout.visibility = View.GONE
            errorLinearLayout.visibility = View.GONE
        }

    }

    private fun showViewLoading() {
        binding?.apply {
            successLinearLayout.visibility = View.GONE
            loadingFrameLayout.visibility = View.VISIBLE
            errorLinearLayout.visibility = View.GONE
        }
    }

    private fun showViewError() {
        binding?.apply {
            successLinearLayout.visibility = View.GONE
            loadingFrameLayout.visibility = View.GONE
            errorLinearLayout.visibility = View.VISIBLE
        }
    }
}