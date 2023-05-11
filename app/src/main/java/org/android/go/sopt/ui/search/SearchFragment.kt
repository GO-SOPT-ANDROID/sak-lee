package org.android.go.sopt.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentSearchBinding
import org.android.go.sopt.util.Debouncer
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.pagingSubmitData


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var binding: FragmentSearchBinding
    private val adapter = SearchPagingAdapter()
    private val searchDebouncer = Debouncer<String>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // test test
        binding.etvSearch.setOnKeyListener { _, keyCode, event ->
            var handled = false
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                initAdapter(binding.etvSearch.text.toString())
                hideKeyboard()
                handled = true
            }
            handled
        }

        binding.etvSearch.doAfterTextChanged { text ->
            searchDebouncer.setDelay(text.toString(), 500L) {
                initAdapter(binding.etvSearch.text.toString())
            }
        }
    }

    private fun initAdapter(query: String) {
        binding.rvKakaoSearchResult.adapter = adapter.apply {
            pagingSubmitData(viewLifecycleOwner, viewModel.getKaKaoResult(query), adapter)
        }
    }

}