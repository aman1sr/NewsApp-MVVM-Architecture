package com.aman.mynewsmvvm_cleanarch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aman.mynewsmvvm_cleanarch.databinding.ActivityMainBinding
import com.aman.mynewsmvvm_cleanarch.ui.PaginationTopHeadlineActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        binding.topHeadlineButton.setOnClickListener {
//            startActivity(TopHeadlineActivity.getStartIntent(this@MainActivity))
//        }

        binding.topHeadlinePaginationButton.setOnClickListener {
            startActivity(PaginationTopHeadlineActivity.getStartIntent(this@MainActivity))
        }
//
//        binding.newsSourceButton.setOnClickListener {
//            startActivity(SourcesActivity.getStartIntent(this@MainActivity))
//        }
//
//        binding.countriesButton.setOnClickListener {
//            startActivity(CountryListActivity.getStartIntent(this@MainActivity))
//        }
//
//        binding.languageButton.setOnClickListener {
//            startActivity(LanguageActivity.getStartIntent(this@MainActivity))
//        }
//
//        binding.searchButton.setOnClickListener {
//            startActivity(SearchActivity.getStartIntent(this@MainActivity))
//        }
    }
}