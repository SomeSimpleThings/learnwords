package com.somethingsimple.learnwords.ui.words.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.somethingsimple.learnwords.LearnWordsApp
import com.somethingsimple.learnwords.R
import com.somethingsimple.learnwords.data.WordlistState
import com.somethingsimple.learnwords.data.vo.Word
import com.somethingsimple.learnwords.databinding.FragmentWordlistBinding
import com.somethingsimple.learnwords.ui.base.BaseFragment
import com.somethingsimple.learnwords.ui.words.WordsViewModel
import javax.inject.Inject


class WordlistFragment : BaseFragment<WordlistState>() {

    private var wordAdapter: WordAdapter? = null
    var binding: FragmentWordlistBinding? = null

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var model: WordsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = viewModelFactory.create(WordsViewModel::class.java)
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
                model.getData(editTextSearch.text.toString(), true)
            }
        }
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.subscribe().observe(viewLifecycleOwner, { renderData(it) })
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as LearnWordsApp).appComponent.inject(this)
        super.onAttach(context)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            WordlistFragment()

    }

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
            model.getData("hi", true)
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