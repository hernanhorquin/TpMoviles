package com.example.tpfinalmoviles.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpfinalmoviles.data.model.Cow
import com.example.tpfinalmoviles.data.repository.CowRepository
import com.example.tpfinalmoviles.utils.Data
import com.example.tpfinalmoviles.utils.Result
import com.example.tpfinalmoviles.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val cowRepository: CowRepository): ViewModel() {

    private var _createCow = MutableLiveData<Data<Cow>>()
    val createCow: LiveData<Data<Cow>>
        get() = _createCow

    private var _getCow = MutableLiveData<Data<Cow>>()
    val getCow: LiveData<Data<Cow>>
        get() = _getCow

    fun createCow() = viewModelScope.launch {
        val cow = Cow(1, 2, 3, "1/01/2021", 1, 5.3, "")
        when (val result = withContext(Dispatchers.IO) { cowRepository.createCow(cow)}) {
            is Result.Failure -> {
                _createCow.postValue(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                _createCow.postValue(Data(responseType = Status.SUCCESSFUL, data = result.data))
            }
        }
    }

    fun getCow() = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) { cowRepository.getCow()}) {
            is Result.Failure -> {
                _getCow.postValue(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                _getCow.postValue(Data(responseType = Status.SUCCESSFUL, data = result.data))
            }
        }
    }

}