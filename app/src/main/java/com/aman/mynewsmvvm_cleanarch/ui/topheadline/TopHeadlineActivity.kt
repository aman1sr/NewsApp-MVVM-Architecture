package com.aman.mynewsmvvm_cleanarch.ui.topheadline

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aman.mynewsmvvm_cleanarch.R
import com.aman.mynewsmvvm_cleanarch.data.local.entity.Article
import com.aman.mynewsmvvm_cleanarch.databinding.ActivityTopHeadlineBinding
import com.aman.mynewsmvvm_cleanarch.ui.base.BaseActivity
import com.aman.mynewsmvvm_cleanarch.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TopHeadlineActivity : BaseActivity<TopHeadlineViewModel,ActivityTopHeadlineBinding>() {
   @Inject
   lateinit var topHeadlineAdapter: TopHeadlineAdapter
    companion object{
        val TAG = "TopHeadLine_d"
       fun getStartIntent(context: Context): Intent{
           return Intent(context,TopHeadlineActivity::class.java)
       }
   }


    override fun setupObserver() {
        super.setupObserver()
        lifecycleScope.launch {
            repeatOnLifecycle((Lifecycle.State.STARTED)){
                viewModel.data.collect{
                    when(it.status){
                        Status.SUCCESS -> {
                            Log.d(TAG, "status: SUCCESS")

                            binding.progressBar.visibility = View.GONE
                            binding.includeLayout.errorLayout.visibility = View.GONE
                            it.data?.let {newsList ->
                                renderList(newsList as List<Article>)
                            }
                            binding.recyclerView.visibility = View.VISIBLE
                        }
                        Status.LOADING -> {
                            Log.d(TAG,"status: LOADING")

                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                            binding.includeLayout.errorLayout.visibility = View.GONE
                        }
                        Status.ERROR -> {
                            Log.d(TAG,"status: ERROR")

                            binding.progressBar.visibility = View.GONE
                            binding.includeLayout.errorLayout.visibility = View.VISIBLE
                            Toast.makeText(this@TopHeadlineActivity, it.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

        }
    }

    private fun renderList(newsList: List<Article>) {
        topHeadlineAdapter.addData(newsList)
        topHeadlineAdapter.notifyDataSetChanged()
    }

    override fun setupViewModel() {
    viewModel = ViewModelProvider(this)[TopHeadlineViewModel::class.java]
    }

    override fun setupView(savedInstanceState: Bundle?) {
        val recView = binding.recyclerView
        recView.apply {
            layoutManager = LinearLayoutManager(this@TopHeadlineActivity)
            addItemDecoration(DividerItemDecoration(recView.context,
                (recView.layoutManager as LinearLayoutManager).orientation))
            adapter = topHeadlineAdapter
        }

        binding.includeLayout.tryAgainBtn.setOnClickListener{
            viewModel.startFetchingNews()
        }
        topHeadlineAdapter.itemClickListener= {pos,data ->
            val article = data as Article
            val builder = CustomTabsIntent.Builder()
            val customTabIntent = builder.build()
            customTabIntent.launchUrl(this@TopHeadlineActivity, Uri.parse(article.url))
        }
    }

    override fun setupViewBinding(inflater: LayoutInflater): ActivityTopHeadlineBinding {
        return ActivityTopHeadlineBinding.inflate(inflater)
    }


}