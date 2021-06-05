package com.emiratesauction.weather.city.presentation.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emiratesauction.weather.R
import com.emiratesauction.weather.city.domain.model.City
import com.emiratesauction.weather.city.presentation.activities.WeatherActivity
import com.emiratesauction.weather.helper.OnItemAdapterClickListener
import com.emiratesauction.weather.helper.loadUrl
import com.emiratesauction.weather.helper.temp
import kotlinx.android.synthetic.main.item_city_save.view.*

/**
 * Created by µðšţãƒâ ™ on 03/06/2021.
 *  ->
 */
class CityAdapter(private val mCallBack: OnItemAdapterClickListener<City>) :
    RecyclerView.Adapter<CityAdapter.CityHolder>() {

    private val mList = mutableListOf<City>()

    fun addCity(items: City) {
        mList.add(items)
        notifyDataSetChanged()
    }

    fun removeItem(item: City) {
        mList.remove(item)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == 1) CityHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_city_remove, parent, false)
        )
        else CityHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_city_save, parent, false)
        )

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: CityHolder, position: Int) =
        holder.bindView(mList[position])

    override fun getItemViewType(position: Int) = mList[position].save ?: 0

    inner class CityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(item: City) {
            itemView.tvCity.text = item.name
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

            itemView.tvDbAction.setOnClickListener {
                item.save = if (item.save == null || item.save == 0) 1 else 0
                mCallBack.onItemAdapterClicked(item)
            }

            itemView.setOnClickListener {
                it.context.startActivity(
                    Intent(it.context, WeatherActivity::class.java)
                        .putExtra("cityName", item.name)
                )
            }
        }
    }
}