package com.ibnu.artalele.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ibnu.artalele.R
import com.ibnu.artalele.databinding.ActivityMainBinding
import com.ibnu.artalele.utils.SharedPreferencesManager

class MainActivity : AppCompatActivity() {
    private var _bindingMainActivity: ActivityMainBinding? = null
    private val binding get() = _bindingMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_bindingMainActivity?.root)

        binding?.bottomNav?.setupWithNavController(findNavController(this, R.id.container_fragment))
        findNavController(
            this,
            R.id.container_fragment
        ).addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.bukuHutangFragment || destination.id == R.id.homeFragment || destination.id == R.id.settingFragment2) {
                binding?.bottomNav?.visibility = View.VISIBLE
            } else {
                binding?.bottomNav?.visibility = View.GONE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        SharedPreferencesManager(this).resetTransactionSegment()
    }
}