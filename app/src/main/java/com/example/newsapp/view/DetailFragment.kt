package com.example.newsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.MainActivity
import com.example.newsapp.databinding.FragmentDetailBinding
import com.example.newsapp.model.Article
import com.example.newsapp.viewModel.NewsViewModel


class DetailFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        val article = arguments?.getSerializable("article") as Article
        binding.webView.loadUrl(article.url)

        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.buttonSave.setOnClickListener {
            viewModel.saveArticle(article)
            val myToast = Toast.makeText(requireContext(), "Article saved!", Toast.LENGTH_SHORT)
            myToast.show()
        }
    }
}