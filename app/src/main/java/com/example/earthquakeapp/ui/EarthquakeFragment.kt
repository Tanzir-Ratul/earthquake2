package com.example.earthquakeapp.ui

import com.example.earthquakeapp.ui.datetime.DatePickerFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earthquakeapp.ui.adapters.EarthquakeAdapter
import com.example.earthquakeapp.ui.viewmodels.EarthquakeViewModel
import com.example.earthquakeapp.databinding.FragmentEarthquakeBinding
import com.example.earthquakeapp.ui.helper.Constant.magnitudeList
import com.example.earthquakeapp.ui.utils.ApiState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EarthquakeFragment @Inject constructor() :
    Fragment() {
    private val viewModel: EarthquakeViewModel by viewModels()
    private lateinit var earthquakeAdapter: EarthquakeAdapter
    private var binding: FragmentEarthquakeBinding? = null
    private var spinnerMagValue = 5.0
    private var fromDate = ""
    private var toDate = ""
    private lateinit var sview:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  FragmentEarthquakeBinding.inflate(inflater, container, false)
       sview = binding?.root!!
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPage()
        apiStateObserver()
        goButtonAction()
        dateOnClickAction()


    }

    private fun initPage() {
        initSpinner()
        initEarthquakeAdapter()

    }

    private fun dateOnClickAction() {
        binding?.fromButton?.setOnClickListener {
            DatePickerFragment {
                viewModel.setStartDate(it)
                fromDate = it
                binding?.fromButton?.text = it
            }.show(childFragmentManager, null)
        }
        binding?.toButton?.setOnClickListener {
            DatePickerFragment {
                viewModel.setEndDate(it)
                toDate = it
                binding?.toButton?.text = it
            }.show(childFragmentManager, null)
        }
    }

    private fun goButtonAction() {
        binding?.goButton?.setOnClickListener {
            try {
                if (fromDate > toDate) {
                    Toast.makeText(requireContext(), "Please enter valid date", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    initEarthquakeAdapter()
                }

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "${e.printStackTrace()}", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    private fun initSpinner() {
        val magnituteAdapter = ArrayAdapter<Int>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            magnitudeList
        )
        binding?.spinner?.adapter = magnituteAdapter
        binding?.spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val temp = p0?.getItemAtPosition(p2).toString()
                spinnerMagValue = temp.toDouble()
                viewModel.setSpinnerViewModel(spinnerMagValue)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

  private  fun apiStateObserver() {
        viewModel.apiState.observe(viewLifecycleOwner) {
            when (it) {
                is ApiState.Failure -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is ApiState.ProgressState -> {
                    if (it.isShow) {
                        binding?.progressBar2?.visibility = View.VISIBLE
                    } else {
                        binding?.progressBar2?.visibility = View.GONE
                    }
                }
            }
        }
    }


    private fun initEarthquakeAdapter() {

        viewModel.getData()
        earthquakeAdapter = EarthquakeAdapter()
        binding?.earthquakeRV?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding?.earthquakeRV?.adapter = earthquakeAdapter
        //Log.d("ko",viewModel.e.toString())
        viewModel.responseBody.observe(viewLifecycleOwner) {
            //Log.d("item", "${ it.features.size }")
            Snackbar.make(sview, "Total item: ${it.features.size}", Snackbar.LENGTH_LONG).show()
           // Toast.makeText(requireContext(), "${it.features.size}", Toast.LENGTH_SHORT).show()
            earthquakeAdapter.submitList(it.features)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}
	

