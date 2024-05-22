package com.aman.mynewsmvvm_cleanarch.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aman.mynewsmvvm_cleanarch.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PaginationTopHeadlineActivity : AppCompatActivity() {
    @Inject
    lateinit var topHeadlineAdapter: PagingTopHeadlineAdapter

    companion object{
        fun getStartIntent(context: Context): Intent{
            return Intent(context,PaginationTopHeadlineActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagination_top_headline)
    }
}