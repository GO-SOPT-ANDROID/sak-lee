package org.android.go.sopt.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import org.android.go.sopt.MainActivity
import org.android.go.sopt.databinding.DialogAlertBinding


class LogoutDialog() : DialogFragment() {
    private val viewModel: MyPageViewModel by viewModels()
    private lateinit var binding: DialogAlertBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogAlertBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBtn()
    }

    private fun initBtn() {
        binding.btnOk.setOnClickListener {
            viewModel.logout(false)
            startMainActivity()
        }
        binding.btnFail.setOnClickListener {
            dismiss()
        }
    }

    private fun startMainActivity() {
        Intent(activity, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
    }
}