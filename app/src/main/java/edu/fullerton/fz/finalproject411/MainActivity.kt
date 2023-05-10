package edu.fullerton.fz.finalproject411

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.fullerton.fz.finalproject411.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CryptoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FavoritesDataStore.initialize(this)
        viewModel = ViewModelProvider(this).get(CryptoViewModel::class.java)

        replaceFragment(Home())

        binding.navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(Home())
                R.id.favorites -> replaceFragment(Favorites())
                R.id.settings -> replaceFragment(Settings())
                else -> {
                }
            }

            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()

        // Log a message for debugging purposes
        println("Switched tab")
    }
}



