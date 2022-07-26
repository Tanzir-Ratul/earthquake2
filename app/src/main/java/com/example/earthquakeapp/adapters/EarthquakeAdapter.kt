package com.example.earthquakeapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakeapp.api.models.Earthquake
import com.example.earthquakeapp.databinding.EarthquakeItemBinding

class EarthquakeAdapter : ListAdapter<Earthquake.Feature, EarthquakeAdapter.EarthquakeViewHolder>(EarthquakeDiffUtil()) {
	
	class EarthquakeViewHolder(private val binding: EarthquakeItemBinding) :
		RecyclerView.ViewHolder(binding.root) {
		
		fun bind(item: Earthquake.Feature) {
			Log.d("adapter",item.properties.place)
			binding.items = item

		}

	}
	
	class EarthquakeDiffUtil : DiffUtil.ItemCallback<Earthquake.Feature>() {
		override fun areItemsTheSame(oldItem: Earthquake.Feature, newItem: Earthquake.Feature): Boolean {
			return oldItem == newItem
		}
		
		override fun areContentsTheSame(oldItem: Earthquake.Feature, newItem: Earthquake.Feature): Boolean {
			return oldItem == newItem
		}



	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthquakeViewHolder {
		val binding = EarthquakeItemBinding.inflate(
			LayoutInflater.from(parent.context), parent, false
		)
		return EarthquakeViewHolder(binding)
	}
	
	override fun onBindViewHolder(holder: EarthquakeViewHolder, position: Int) {
		val item = getItem(position)
		Log.d("adapter2",item.properties.place)
		holder.bind(item)
	}
}