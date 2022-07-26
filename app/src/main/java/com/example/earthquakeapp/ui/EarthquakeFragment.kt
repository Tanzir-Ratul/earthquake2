package com.example.earthquakeapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earthquakeapp.adapters.EarthquakeAdapter
import com.example.earthquakeapp.api.models.Earthquake
import com.example.earthquakeapp.utils.ApiState
import com.example.earthquakeapp.viewmodels.EarthquakeViewModel
import com.example.earthquakeapp.databinding.FragmentEarthquakeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EarthquakeFragment @Inject constructor() :
	Fragment() {
	private val viewModel: EarthquakeViewModel by viewModels()
	private lateinit var earthquakeAdapter: EarthquakeAdapter
	private lateinit var binding: FragmentEarthquakeBinding
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		
		binding = FragmentEarthquakeBinding.inflate(inflater, container, false)
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		//initEarthquakeAdapter()
//		val e  = viewModel.getData()
//		Log.d("data", e.toString())
		earthquakeAdapter = EarthquakeAdapter()
		binding.earthquakeRV.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
		binding.earthquakeRV.adapter = earthquakeAdapter
		Log.d("ko",viewModel.e.toString())
		viewModel.earthquakeLiveData.observe(viewLifecycleOwner){
		    Log.d("item", "${ it.features.size }")
			earthquakeAdapter.submitList(it.features)
		}



	}
	

	

	
	
	/*
	private fun earthquakeStateFlow() {
		lifecycleScope.launchWhenCreated {
			viewModel.earthquakeLiveData.observe(viewLifecycleOwner) {
				when (it) {

					Log.d("data", viewModel.getData().toString())
					is ApiState.Loading -> {
						binding.progressBar2.isVisible = true
						binding.earthquakeRV.isVisible = false
					}
					is ApiState.Failure -> {
						binding.progressBar2.isVisible = false
						binding.earthquakeRV.isVisible = false
						Toast.makeText(requireContext(), "Check your internet connection!", Toast.LENGTH_SHORT)
							.show()
					}
					is ApiState.Success -> {
						binding.progressBar2.isVisible = false
						binding.earthquakeRV.isVisible = true
						Toast.makeText(
							requireContext(),
							"Data fetch successful",
							Toast.LENGTH_SHORT
						).show()
						viewModel.earthquakeLiveData.observe(viewLifecycleOwner){
							earthquakeAdapter.submitList(it.features)
						}
						//earthquakeAdapter.submitList(listOf())	//Log.d("data",viewModel.getData().toString())
					}
					is ApiState.Empty -> {

					}

				}
			}
		}*/
	private fun initEarthquakeAdapter() {
		earthquakeAdapter = EarthquakeAdapter()
		binding.earthquakeRV.layoutManager = LinearLayoutManager(requireContext())
		binding.earthquakeRV.adapter = earthquakeAdapter
	}


}
	

