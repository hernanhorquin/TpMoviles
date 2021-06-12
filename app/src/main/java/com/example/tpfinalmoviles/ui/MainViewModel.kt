package com.example.tpfinalmoviles.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpfinalmoviles.data.model.*
import com.example.tpfinalmoviles.data.repository.AppRepository
import com.example.tpfinalmoviles.utils.Data
import com.example.tpfinalmoviles.utils.Result
import com.example.tpfinalmoviles.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val appRepository: AppRepository): ViewModel() {

    private var _createCow = MutableLiveData<Data<Cow>>()
    val createCow: LiveData<Data<Cow>>
        get() = _createCow

    private var _getCow = MutableLiveData<Data<Cow>>()
    val getCow: LiveData<Data<Cow>>
        get() = _getCow

    private var _createHerd = MutableLiveData<Data<Herd>>()
    val createHerd: LiveData<Data<Herd>>
        get() = _createHerd

    private var _getHerd = MutableLiveData<Data<Herd>>()
    val getHerd: LiveData<Data<Herd>>
        get() = _getHerd

    private var _addCowAlert = MutableLiveData<Data<CowAlert>>()
    val addCowAlert: LiveData<Data<CowAlert>>
        get() = _addCowAlert

    private var _addHerdAlert = MutableLiveData<Data<HerdAlert>>()
    val addHerdAlert: LiveData<Data<HerdAlert>>
        get() = _addHerdAlert

    private var _getCowAlerts = MutableLiveData<Data<List<CowFiredAlert>>>()
    val getCowAlerts: LiveData<Data<List<CowFiredAlert>>>
        get() = _getCowAlerts

    private var _getHerdAlerts = MutableLiveData<Data<List<HerdFiredAlert>>>()
    val getHerdAlerts: LiveData<Data<List<HerdFiredAlert>>>
        get() = _getHerdAlerts

    private var _toggleSession = MutableLiveData<Data<Boolean>>()
    val toggleSession: LiveData<Data<Boolean>>
        get() = _toggleSession

    fun createHerd(herd: Herd) = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) {
            appRepository.createHerd(herd)
        }) {
            is Result.Failure -> {
                _createHerd.postValue(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                _createHerd.postValue(Data(responseType = Status.SUCCESSFUL, data = result.data))
            }
        }
    }

    fun getHerd(id: Int) = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) {
            appRepository.getHerd(id)
        }) {
            is Result.Failure -> {
                _getHerd.postValue(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                _getHerd.postValue(Data(responseType = Status.SUCCESSFUL, data = result.data))
            }
        }
    }

    fun createCow(cow: Cow) = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) {
            appRepository.createCow(cow)
        }) {
            is Result.Failure -> {
                _createCow.postValue(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                _createCow.postValue(Data(responseType = Status.SUCCESSFUL, data = result.data))
            }
        }
    }

    fun getCow(id: Int) = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) {
            appRepository.getCow(id)
        }) {
            is Result.Failure -> {
                _getCow.postValue(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                _getCow.postValue(Data(responseType = Status.SUCCESSFUL, data = result.data))
            }
        }
    }

    fun addCowAlert(cowAlert: CowAlert) = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) {
            appRepository.addCowAlert(cowAlert)
        }) {
            is Result.Failure -> {
                _addCowAlert.postValue(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                _addCowAlert.postValue(Data(responseType = Status.SUCCESSFUL, data = result.data))
            }
        }
    }

    fun addHerdAlert(herdAlert: HerdAlert) = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) {
            appRepository.addHerdAlert(herdAlert)
        }) {
            is Result.Failure -> {
                _addHerdAlert.postValue(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                _addHerdAlert.postValue(Data(responseType = Status.SUCCESSFUL, data = result.data))
            }
        }
    }

    fun getHerdAlerts() = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) {
            appRepository.getHerdAlerts()
        }) {
            is Result.Failure -> {
                _getHerdAlerts.postValue(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                _getHerdAlerts.postValue(Data(responseType = Status.SUCCESSFUL, data = result.data))
            }
        }
    }

    fun getCowAlerts() = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) {
            appRepository.getCowAlerts()
        }) {
            is Result.Failure -> {
                _getCowAlerts.postValue(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                _getCowAlerts.postValue(Data(responseType = Status.SUCCESSFUL, data = result.data))
            }
        }
    }

    fun toggleSession(session: Session) = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) {
            appRepository.toggleSession(session)
        }) {
            is Result.Failure -> {
                _toggleSession.postValue(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                _toggleSession.postValue(Data(responseType = Status.SUCCESSFUL, data = result.data))
            }
        }
    }
}