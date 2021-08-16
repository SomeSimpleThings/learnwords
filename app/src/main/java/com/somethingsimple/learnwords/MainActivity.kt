package com.somethingsimple.learnwords

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.somethingsimple.history.ui.HistoryFragment
import com.somethingsimple.learnwords.databinding.ActivityMainBinding
import com.somethingsimple.learnwords.ui.words.list.WordlistFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.fab.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, HistoryFragment.newInstance())
                .commit()
        }
        val view = binding.root
        setContentView(view)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, WordlistFragment.newInstance())
            .commit()
    }
}