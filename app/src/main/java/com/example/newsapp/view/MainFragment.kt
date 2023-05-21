package com.example.newsapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.MainActivity
import com.example.newsapp.R
import com.example.newsapp.RecyclerViewAdapter
import com.example.newsapp.databinding.FragmentMainBinding
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.util.Resource
import com.example.newsapp.viewModel.NewsViewModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var newsAdapter: RecyclerViewAdapter
    private lateinit var viewModel: NewsViewModel


    val TAG = "MainFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_mainFragment_to_detailFragment,
                bundle
            )
        }

        binding.savedNews.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_savedFragment)
        }
        binding.reload.setOnClickListener {
            showProgressBar()
            viewModel.updateNews()
        }
        viewModel.news.observe(viewLifecycleOwner, Observer { response ->
            handleNewsResponse(response)
        })
//        search()
        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            handleNewsResponse(response)
        })
    }

    private fun handleNewsResponse(response: Resource<NewsResponse>) {
        when (response) {
            is Resource.Success -> {
                hideProgressBar()
                response.data?.let { newsResponse ->
                    newsAdapter.differ.submitList(newsResponse.articles)
                }
            }
            is Resource.Error -> {
                hideProgressBar()
                response.message?.let { message ->
                    Log.e(TAG, "An error occurred: $message")
                }
            }
            is Resource.Loading -> {
                showProgressBar()
            }
        }
    }

//    private fun performSearch(query: String) {
//        viewModel.searchNews(query)
//    }

//    private fun search() {
//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                query?.let {
//                    performSearch(it)
//                }
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                newText?.let { performSearch(it) }
//                return false
//            }
//        })
//    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter = RecyclerViewAdapter()
        binding.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
