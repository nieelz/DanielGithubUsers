package com.dicoding.picodiploma.danielgithubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.danielgithubusers.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val USER_ID = "sidiqpermana"

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


        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs:TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager){ tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f

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
}