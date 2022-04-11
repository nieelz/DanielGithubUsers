package com.dicoding.picodiploma.danielgithubusers.ui.setting

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.lifecycle.lifecycleScope
import com.dicoding.picodiploma.danielgithubusers.R
import com.dicoding.picodiploma.danielgithubusers.databinding.ActivitySettingsBinding
import com.dicoding.picodiploma.danielgithubusers.ui.favorite.FavoriteActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private var darkMode = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getThemeApp()

        binding.buttonDark.setOnClickListener {
            darkMode = !darkMode
            putThemeData(darkMode)
        }
    }


    private fun getThemeApp() {
        lifecycleScope.launch {
            SettingPreferences.getThemes(this@SettingsActivity).collect {
                darkMode = it
                setThemeApp(it)
            }
        }
    }

    private fun setThemeApp(isDarkMode: Boolean) {
        setDefaultNightMode(if (isDarkMode) MODE_NIGHT_NO else MODE_NIGHT_YES)
    }


    private fun putThemeData(isDarkMode: Boolean) {
        lifecycleScope.launch {
            SettingPreferences.updateThemes(this@SettingsActivity, isDarkMode)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu1 -> {
                val i = Intent(this, FavoriteActivity::class.java)
                startActivity(i)
                true
            }
            R.id.menu2 -> {
                val i = Intent(this, SettingsActivity::class.java)
                startActivity(i)
                true
            }
            else -> true
        }
    }


}


