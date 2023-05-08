package org.android.go.sopt.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.data.model.FakeGithubInfo
import org.android.go.sopt.ui.join.JoinViewModel
import org.android.go.sopt.util.AssetLoader
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel by viewModels<HomeViewModel>()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter = HomeAdapter()

    private val selectionTracker by lazy {
        with(binding) {
            SelectionTracker.Builder(
                "selection_id",//객체의 ID
                rvFakeGithubInfo, // 적용될 Rv
                StableIdKeyProvider(rvFakeGithubInfo),//ItemKeyProvider 라이브러리에서 제공.커스텀 필요하는 경우 있음
                HomeAdapter.HomeLookUp(rvFakeGithubInfo), //  RecyclerView 아이템의 대한 정보
                StorageStrategy.createLongStorage()//선택항목 저장 전략 라이브러리 제공
            )
                .withSelectionPredicate(SelectionPredicates.createSelectAnything())
                .build()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fakeGithubAsset = loadFakeGithubInfo()

        initAdapter(fakeGithubAsset)

    }

    private fun loadFakeGithubInfo(): FakeGithubInfo? {
        val asserLoader = AssetLoader(requireContext())
        val fakeGithubAssetLoader = asserLoader.getJsonString("fake_repo_list.json")
        val gson = Gson()
        viewModel.getUserList()
        return gson.fromJson(fakeGithubAssetLoader, FakeGithubInfo::class.java)
    }

    private fun initAdapter(fakeGithubAsset: FakeGithubInfo?) {
        binding.rvFakeGithubInfo.adapter = adapter.apply {
            submitList(fakeGithubAsset)
        }
        adapter.setSelectionTracker(selectionTracker)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

