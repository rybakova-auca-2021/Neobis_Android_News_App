package com.example.newsapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.MainActivity
import com.example.newsapp.R
import com.example.newsapp.RecyclerViewAdapter
import com.example.newsapp.databinding.FragmentMainBinding
import com.example.newsapp.databinding.FragmentSavedBinding
import com.example.newsapp.viewModel.NewsViewModel

class SavedFragment : Fragment() {

    companion object {
        fun newInstance() = SavedFragment()
    }
    private lateinit var binding: FragmentSavedBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_savedFragment_to_detailFragment,
                bundle
            )
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun setupRecyclerView() {
        newsAdapter = RecyclerViewAdapter()
        binding.rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}