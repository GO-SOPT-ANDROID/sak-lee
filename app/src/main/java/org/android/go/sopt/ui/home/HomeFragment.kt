package org.android.go.sopt.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import com.google.gson.Gson
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.model.FakeGithubInfo
import org.android.go.sopt.util.AssetLoader

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val adapter = HomeAdapter()

    /**
     *아이템 선택 상태 추적
     * 현재 선택 내용 검사
     * 임시 선택 모두가 지워지는 문제 해결
     * Recyclerview와 사용
     */

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
        binding =
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

        return gson.fromJson(fakeGithubAssetLoader, FakeGithubInfo::class.java)
    }

    private fun initAdapter(fakeGithubAsset: FakeGithubInfo?) {

        binding.rvFakeGithubInfo.adapter = adapter.apply {
            submitList(fakeGithubAsset)
        }

        adapter.setSelectionTracker(selectionTracker)

    }

}

