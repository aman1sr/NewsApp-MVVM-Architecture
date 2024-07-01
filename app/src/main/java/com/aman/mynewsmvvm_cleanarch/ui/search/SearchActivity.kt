package com.aman.mynewsmvvm_cleanarch.ui.search

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aman.mynewsmvvm_cleanarch.R
import com.aman.mynewsmvvm_cleanarch.data.model.topheadlines.APIArticle
import com.aman.mynewsmvvm_cleanarch.databinding.ActivitySearchBinding
import com.aman.mynewsmvvm_cleanarch.ui.base.BaseActivity
import com.aman.mynewsmvvm_cleanarch.ui.newsListScreen.NewsListAdapter
import com.aman.mynewsmvvm_cleanarch.utils.Status
import com.aman.mynewsmvvm_cleanarch.utils.getQueryTextChangeStateFlow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
@AndroidEntryPoint
class SearchActivity : BaseActivity<SearchViewModel,ActivitySearchBinding>() {
    @Inject
    lateinit var newsListAdapter : NewsListAdapter
    companion object{
        val TAG = "SearchNews_d"
        fun getStartIntent(context: Context): Intent{
            return Intent(context,SearchActivity::class.java)
        }
    }

    override fun setupObserver() {
        super.setupObserver()
        viewModel.setUpSearchStateFlow(
            binding.searchView.getQueryTextChangeStateFlow()        //use of Extension f() to return Flow
        )

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.data.collect{
                when(it.status){
                    Status.SUCCESS -> {
                        binding.includeLayout.errorLayout.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        it.data?.let { newsList ->
                            renderList(newsList as List<APIArticle>)
                        }
                        binding.recyclerView.visibility = View.VISIBLE
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                        binding.includeLayout.errorLayout.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        binding.includeLayout.errorLayout.visibility = View.VISIBLE
                        Toast.makeText(this@SearchActivity, it.message, Toast.LENGTH_LONG)
                            .show()
                    }
                }
                }
            }
        }
    }
    private fun renderList(newList: List<APIArticle>) {
        newsListAdapter.replaceData(newList)
        newsListAdapter.notifyDataSetChanged()
    }
    override fun setupViewModel() {
       viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
    }

    override fun setupView(savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerView
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
            adapter = newsListAdapter
        }
        binding.includeLayout.tryAgainBtn.setOnClickListener {
            viewModel.fetchNewsByQueries(binding.searchView.query.toString())
        }

        newsListAdapter.itemClickListener = { _, data ->
            val article = data as APIArticle
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this@SearchActivity, Uri.parse(article.url))
        }
    }

    override fun setupViewBinding(inflater: LayoutInflater): ActivitySearchBinding {
       return ActivitySearchBinding.inflate(inflater)
    }


}