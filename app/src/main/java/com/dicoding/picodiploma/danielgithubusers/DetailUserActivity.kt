package com.dicoding.picodiploma.danielgithubusers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.danielgithubusers.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val USER_ID = "nieelz"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )

    }

    private var _binding: ActivityDetailUserBinding?=null
    private val binding get() = _binding!!




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailUserBinding.inflate(layoutInflater)
        val id = intent.getStringExtra(USER_ID) as String

        val viewmodel = ViewModelProvider(this, ViewModelFactory(id)).get(ViewModelDetail::class.java)

        viewmodel.detailAccount().observe(this){
            showUserData(it)
        }
        viewmodel.isLoading.observe(this, {
            showLoading(it)
        })


        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs:TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager){ tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
        binding.buttonShare.setOnClickListener{
            val sendIntent = Intent(Intent.ACTION_SEND)
            sendIntent.type = "text/plain"
            sendIntent.putExtra(Intent.EXTRA_TEXT, "My application name")
            val shareMessage = "Share profil ini"

            sendIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            val shareIntentTo = Intent.createChooser(sendIntent, "title")
            startActivity(shareIntentTo)
        }

        setContentView(binding.root)
    }

    private fun showUserData(user: DetailUserResponse) {
        binding.tvUser.text = user.login
        binding.tvFullname.text = user.name
        binding.tvCompany.text = user.company
        binding.tvLocation.text = user.location
        binding.tvFollowing.text = user.following.toString()
        binding.tvFollowers.text = user.followers.toString()
        binding.tvRepository.text = user.publicRepos.toString()
        Glide.with(binding.imageView).load(user.avatarUrl).into(binding.imageView)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}