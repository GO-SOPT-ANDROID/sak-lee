package org.android.go.sopt

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.util.User
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = setupNavigation()
        setBottomVisible(navController)

        setUser()
    }

    private fun setupNavigation(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        binding.bottomNavigation.setOnItemReselectedListener {
            if (it.itemId == R.id.navigation_home) navController.navigate(it.itemId)
        }

        return navController
    }

    private fun setUser() {
        if (App.prefs.isLogin) User.login(App.prefs.getUserInfo())
    }
    private fun setBottomVisible(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.visibility = if (destination.id in listOf(
                    R.id.navigation_home,
                    R.id.navigation_gallery,
                    R.id.navigation_search,
                    R.id.navigation_my_page
                )
            ) View.VISIBLE else View.GONE
        }
    }


}

