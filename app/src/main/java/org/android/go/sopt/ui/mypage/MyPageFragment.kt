package org.android.go.sopt.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentMypageBinding

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMypageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_mypage, container, false)
        return binding.root
    }
}