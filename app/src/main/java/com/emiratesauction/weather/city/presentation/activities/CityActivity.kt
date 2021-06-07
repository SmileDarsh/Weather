package com.emiratesauction.weather.city.presentation.activities

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.emiratesauction.weather.R
import com.emiratesauction.weather.city.domain.model.City
import com.emiratesauction.weather.city.presentation.adapters.CityAdapter
import com.emiratesauction.weather.city.presentation.viewModel.CityViewModel
import com.emiratesauction.weather.city.presentation.viewModel.state.WeatherVS
import com.emiratesauction.weather.helper.OnItemAdapterClickListener
import com.emiratesauction.weather.helper.hideSoftInput
import kotlinx.android.synthetic.main.activity_city.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityActivity : AppCompatActivity(), OnItemAdapterClickListener<City> {
    private val mViewModel: CityViewModel by viewModel()
    private val mAdapter = CityAdapter(this)
    private var mText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        mViewModel.viewState.observe(this) {
            when (it) {
                is WeatherVS.AddCity -> {
                    mAdapter.addCity(it.city)
                    cpProgress?.hide()
                }
                is WeatherVS.Empty -> {
                    cpProgress?.showMessageError(R.string.search_for_city)
                }
                is WeatherVS.SaveCity -> {
                    Toast.makeText(this@CityActivity, R.string.save_city, Toast.LENGTH_SHORT).show()
                    clearText()
                }
                is WeatherVS.RemoveCity -> {
                    Toast.makeText(this@CityActivity, R.string.remove_city, Toast.LENGTH_SHORT)
                        .show()
                    mAdapter.removeItem(it.city)
                }
                is WeatherVS.Error -> {
                    cpProgress?.showMessageError(it.message)
                }
                is WeatherVS.InternetError -> {
                    cpProgress?.noInternetFound { getCities() }
                }
            }
        }

        initData()
        cityRecyclerView()
        getCities()
    }

    private fun initData() {
        etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                etSearch.hideSoftInput()
                mText = etSearch.text.toString().trim()
                cpProgress?.hideMessageView()
                getCities()
            }
            true
        }
        ibnClear.setOnClickListener { clearText() }
    }

    private fun getCities() {
        mAdapter.clearItems()
        mViewModel.city(mText)
    }

    private fun clearText(){
        etSearch.hideSoftInput()
        etSearch.text.clear()
        mText = ""
        cpProgress?.hideMessageView()
        getCities()
    }

    private fun cityRecyclerView() {
        rvCity?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    override fun onItemAdapterClicked(item: City) {
        if (item.save == 1) mViewModel.saveCity(item)
        else mViewModel.removeCity(item)
    }
}