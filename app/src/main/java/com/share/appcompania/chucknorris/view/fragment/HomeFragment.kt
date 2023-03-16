package com.share.appcompania.chucknorris.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.share.appcompania.chucknorris.R
import com.share.appcompania.chucknorris.databinding.FragmentHomeBinding
import com.share.appcompania.chucknorris.view.adapter.CategoryAdapter
import com.share.appcompania.chucknorris.viewModel.HomeViewModel

class HomeFragment : Fragment(), CategoryAdapter.CategoriesListener {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterCategories: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCategories()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initObservers()
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        viewModel.responseGetCategories.observe(viewLifecycleOwner) { response ->
            if (!response.isNullOrEmpty()) {
                adapterCategories = CategoryAdapter(response!!, this)
                binding.rvCategories.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                val adapter = adapterCategories
                binding.rvCategories.adapter = adapter
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(requireContext(), "Falló el servicio", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(category: String) {
      findNavController().navigate(R.id.detailFragment, bundleOf("category" to category))
    }
}