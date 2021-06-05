package com.emiratesauction.weather.city.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emiratesauction.weather.R
import com.emiratesauction.weather.city.domain.model.City
import com.emiratesauction.weather.helper.date
import com.emiratesauction.weather.helper.loadUrl
import com.emiratesauction.weather.helper.temp
import kotlinx.android.synthetic.main.item_weather.view.*

/**
 * Created by µðšţãƒâ ™ on 03/06/2021.
 *  ->
 */
class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {

    private val mList = mutableListOf<City>()

    fun addWeathers(items: MutableList<City>) {
        mList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WeatherHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
    )

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) =
        holder.bindView(mList[position])

    inner class WeatherHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(item: City) {
            itemView.tvDate.date(item.dt)
            item.weather?.let {
                itemView.ivIcon.loadUrl(it.first().icon)
                itemView.tvMain.text = it.first().main
                itemView.tvDescription.text = it.first().description
            }
            itemView.tvTemp.temp(item.main?.temp)
            itemView.tvFeelsLike.text =
                itemView.context.getString(R.string.feels_like, (item.main?.feels_like ?: 0.0) / 10)
            itemView.tvWind.text = String.format("%.0f", item.wind?.speed)
            itemView.tvTempMax.temp(item.main?.temp_max)
            itemView.tvTempMin.temp(item.main?.temp_min)
        }
    }
}