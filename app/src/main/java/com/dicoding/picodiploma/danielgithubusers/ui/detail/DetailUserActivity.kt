package com.dicoding.picodiploma.danielgithubusers.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.danielgithubusers.R
import com.dicoding.picodiploma.danielgithubusers.database.local.FavoriteEntity
import com.dicoding.picodiploma.danielgithubusers.database.remote.response.DetailUserResponse
import com.dicoding.picodiploma.danielgithubusers.databinding.ActivityDetailUserBinding
import com.dicoding.picodiploma.danielgithubusers.ui.favorite.FavoriteActivity
import com.dicoding.picodiploma.danielgithubusers.ui.setting.SettingsActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {


    private var _binding: ActivityDetailUserBinding? = null
    private val binding get() = _binding!!
    private val viewModelDetail: ViewModelDetail by viewModels { ViewModelFactory.getInstance(this) }

    private lateinit var currentUser: DetailUserResponse


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.title = "Github Users"
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val id = intent.getStringExtra(USER_ID) as String

        viewModelDetail.setUserData(id)
        viewModelDetail.setFollowingData(id)
        viewModelDetail.setFollowersData(id)

        viewModelDetail.detailAccount().observe(this) {
            showUserData(it)
        }
        viewModelDetail.isLoading.observe(this) {
            showLoading(it)
        }


        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f


        binding.buttonShare.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_SEND)
            sendIntent.type = "text/plain"
            sendIntent.putExtra(Intent.EXTRA_TEXT, "My application name")
            val shareMessage = "Share profil ini"

            sendIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            val shareIntentTo = Intent.createChooser(sendIntent, "title")
            startActivity(shareIntentTo)
        }


        binding.buttonFavorite.setOnClickListener {
            viewModelDetail.insert(
                FavoriteEntity(
                    currentUser.id, currentUser.avatarUrl, currentUser.login, isBookmarked = true)
            )
            showToast(getString(R.string.addedFavorite))
        }


        binding.buttonDelet.setOnClickListener{
            viewModelDetail.delete(FavoriteEntity( currentUser.id, currentUser.avatarUrl, currentUser.login, isBookmarked = true)
            )
            showToast(getString(R.string.delete))
        }

    }




    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun showUserData(user: DetailUserResponse) {

        currentUser = user

        binding.tvUser.text = user.login
        binding.tvFullname.text = user.name
        binding.tvCompany.text = user.company
        binding.tvLocation.text = user.location
        binding.tvFollowing.text = user.following.toString()
        binding.tvFollowers.text = user.followers.toString()
        binding.tvRepository.text = user.publicRepos.toString()
        Glide.with(binding.imageView).load(user.avatarUrl).into(binding.imageView)


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


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


    companion object {
        const val USER_ID = "nieelz"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )

    }


}