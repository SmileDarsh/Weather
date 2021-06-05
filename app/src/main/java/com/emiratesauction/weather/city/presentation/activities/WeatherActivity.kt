package com.emiratesauction.weather.city.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.emiratesauction.weather.R
import com.emiratesauction.weather.city.presentation.adapters.WeatherAdapter
import com.emiratesauction.weather.city.presentation.viewModel.WeatherViewModel
import com.emiratesauction.weather.city.presentation.viewModel.state.WeatherVS
import kotlinx.android.synthetic.main.activity_weather.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : AppCompatActivity() {
    private val mViewModel: WeatherViewModel by viewModel()
    private val mAdapter = WeatherAdapter()
    private var mCityName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        mCityName = intent.getStringExtra("cityName")!!

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mViewModel.viewState.observe(this) {
            when (it) {
                is WeatherVS.AddWeathers -> {
                    mAdapter.addWeathers(it.weather)
                    cpProgress?.hide()
                }
                is WeatherVS.Empty -> {
                    cpProgress?.showMessageError(R.string.no_weathers)
                }
                is WeatherVS.InternetError -> {
                    cpProgress?.noInternetFound { mViewModel.weathers(mCityName) }
                }
            }
        }

        weatherRecyclerView()
        mViewModel.weathers(mCityName)
    }

    private fun weatherRecyclerView() {
        rvWeather?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }
}