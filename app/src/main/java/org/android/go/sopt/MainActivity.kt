package org.android.go.sopt

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import org.android.go.sopt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = setupNavigation()
        setBottomVisible(navController)

    }

    private fun setupNavigation(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
//        val navigator =
//            KeepStateNavigator(this, navHostFragment.childFragmentManager, R.id.fragment_container)
//        navController.navigatorProvider.addNavigator(navigator)
//        navController.setGraph(R.navigation.nav_graph)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        binding.bottomNavigation.setOnItemReselectedListener {
            if (it.itemId == R.id.navigation_home) navController.navigate(it.itemId)
        }

        return navController
    }


    private fun setBottomVisible(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.visibility = if (destination.id in listOf(
                    R.id.navigation_home,
                    R.id.navigation_gallery,
                    R.id.navigation_search
                )
            ) View.VISIBLE else View.GONE
        }
    }


}

