package org.android.go.sopt.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.android.go.sopt.R
import org.android.go.sopt.data.model.kakao.KaKaoImage
import org.android.go.sopt.databinding.FragmentSearchBinding
import org.android.go.sopt.util.hideKeyboard


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var binding: FragmentSearchBinding
    private val adapter = SearchPagingAdapter()
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
    }

    private fun initAdapter(query: String) {
        binding.rvKakaoSearchResult.adapter = adapter.apply {
            productSubmitData(adapter, viewModel.getKaKaoResult(query))
        }
    }

    private fun productSubmitData(
        pagingAdapter: SearchPagingAdapter,
        getData: Flow<PagingData<KaKaoImage>>
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    getData.collectLatest { pagingData ->
                        pagingAdapter.submitData(pagingData)
                    }
                }
            }
        }
    }
}