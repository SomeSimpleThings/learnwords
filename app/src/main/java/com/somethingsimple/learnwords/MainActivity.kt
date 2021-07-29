package com.somethingsimple.learnwords

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.somethingsimple.learnwords.ui.words.list.WordlistFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, WordlistFragment.newInstance())
            .commit()
    }
}