package org.android.go.sopt.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentMypageBinding
import org.android.go.sopt.ui.login.LoginActivity
import org.android.go.sopt.util.User

class MyPageFragment : Fragment() {

    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MyPageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_mypage, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUser()
        initLogoutBtn()
        changeLogout()
    }

    private fun changeLogout() {
        viewModel.isLogin.observe(viewLifecycleOwner) {
            setUser()
        }
    }

    private fun setUser() {
        if (viewModel.isLogin.value == true) binding.user = User.user?.value
        else startLoginActivity()
    }

    private fun startLoginActivity() {
        Intent(activity, LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
    }

    private fun initLogoutBtn() {
        binding.btnLogout.setOnClickListener {
            val dialog = LogoutDialog()
            dialog.show(parentFragmentManager, "dialog")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}