package org.android.go.sopt

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import org.android.go.sopt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController =
            (supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment).navController
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.visibility = if (destination.id in listOf(
                    R.id.navigation_home,
                    R.id.navigation_gallery,
                    R.id.navigation_search
                )
            ) View.VISIBLE else View.GONE
        }

        binding.bottomNavigation.setOnItemReselectedListener {
            if (it.itemId == R.id.navigation_home) navController.navigate(it.itemId)
        }
    }
}

