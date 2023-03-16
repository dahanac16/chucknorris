package com.share.appcompania.chucknorris.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.share.appcompania.chucknorris.R
import com.share.appcompania.chucknorris.databinding.FragmentDetailBinding
import com.share.appcompania.chucknorris.view.Activity.MainActivity
import com.share.appcompania.chucknorris.viewModel.DetailViewModel


class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: FragmentDetailBinding
    private var category = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            arguments?.let {
                category = it.getString("category") as String
                viewModel.getRandomCategories(category)
            }
        } catch (e: java.lang.Exception){
            Log.e("Exception Arguments", e.toString())
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        initObservers()
        initOnClick()
        return binding.root
    }
    private fun initOnClick(){
        binding.back.setOnClickListener{
            (activity as MainActivity).onBackPressed()
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        viewModel.responseGetRandomCategories.observe(viewLifecycleOwner) { response ->
            if (response != null){
                if (response.icon_url != null && response.icon_url == ""){
                    Glide.with(requireContext()).load(response.icon_url).into(binding.imageView)
                }else
                    binding.imageView.setImageResource(R.drawable.imagen)
                if (response.categories?.first() != null){
                    binding.titleCategory.text = response.categories.first()
                }
                if (response.updated_at != null){
                    binding.dateCategory.text = response.updated_at
                }
                if (response.value != null){
                    binding.description.text = response.value
                }
            } else {
                Toast.makeText(requireContext(), "Falló el servicio", Toast.LENGTH_SHORT).show()
            }
        }
    }

}