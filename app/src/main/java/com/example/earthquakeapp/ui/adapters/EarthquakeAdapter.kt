package com.example.earthquakeapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakeapp.api.models.Earthquake
import com.example.earthquakeapp.databinding.EarthquakeItemBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class EarthquakeAdapter : ListAdapter<Earthquake.Feature, EarthquakeAdapter.EarthquakeViewHolder>(
	EarthquakeDiffUtil()
) {
//	val df = DecimalFormat("#.##")
//	df.roundingMode = RoundingMode.DOWN
	class EarthquakeViewHolder(private val binding: EarthquakeItemBinding) :
		RecyclerView.ViewHolder(binding.root) {
		
		fun bind(item: Earthquake.Feature) {
            //val pattern = "MMM dd,yyyy HH:mm"
			//val temp = item.properties.time
			binding.magTV.text = String.format("%.2f", item.properties.mag)
			binding.titleTV.text = item.properties.title
			binding.cityTV.text = item.properties.place
			binding.timeTV.text = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z").format(Date(item.properties.time)).toString()
			//Log.d("adapter",time)
			//binding.items = item

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
		//Log.d("adapter2",item.properties.place)
		holder.bind(item)
	}
}