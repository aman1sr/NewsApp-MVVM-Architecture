package com.aman.mynewsmvvm_cleanarch.ui.pagination

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.aman.mynewsmvvm_cleanarch.R
import com.aman.mynewsmvvm_cleanarch.databinding.ActivityPaginationTopHeadlineBinding
import com.aman.mynewsmvvm_cleanarch.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PaginationTopHeadlineActivity : BaseActivity<PagingTopHeadlineViewModel,ActivityPaginationTopHeadlineBinding>() {
    @Inject
    lateinit var topHeadlineAdapter: PagingTopHeadlineAdapter

    companion object{
        fun getStartIntent(context: Context): Intent{
            return Intent(context, PaginationTopHeadlineActivity::class.java)
        }
        val TAG = "PaginationTopHeadlineActivity_d"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagination_top_headline)
    }

    override fun setupViewModel() {
        viewModel = ViewModelProvider(this)[PagingTopHeadlineViewModel::class.java]
    }

    override fun setupView(savedInstanceState: Bundle?) {
        // setup Rec View
    }

    override fun setupViewBinding(inflater: LayoutInflater):
        ActivityPaginationTopHeadlineBinding {
            return ActivityPaginationTopHeadlineBinding.inflate(inflater)
        }


    override fun setupObserver() {
        super.setupObserver()
        // observe paging Data Flow

    }
}