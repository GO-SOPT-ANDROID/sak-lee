package org.android.go.sopt.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.model.FakeGithubInfo
import org.android.go.sopt.util.AssetLoader

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

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

        val asserLoader = AssetLoader(requireContext())
        val fakeGithubAssetLoader = asserLoader.getJsonString("fake_repo_list.json")
        val gson = Gson()
        val fakeGithubAsset = gson.fromJson(fakeGithubAssetLoader, FakeGithubInfo::class.java)
        Log.d("fake",fakeGithubAsset.toString())

    }
}