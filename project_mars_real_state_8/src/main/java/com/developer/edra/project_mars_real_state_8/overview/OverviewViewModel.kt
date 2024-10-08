/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.developer.edra.project_mars_real_state_8.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.edra.project_mars_real_state_8.network.MarsApi
import com.developer.edra.project_mars_real_state_8.network.MarsApiFilter
import com.developer.edra.project_mars_real_state_8.network.MarsProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class MarsApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = MutableLiveData<MarsApiStatus>()

    val status: LiveData<MarsApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<MarsProperty>>()

    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<MarsProperty?>()


    val navigateToSelectedProperty: MutableLiveData<MarsProperty?>
        get() = _navigateToSelectedProperty


    init {
        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)
    }


    private fun getMarsRealEstateProperties(filter: MarsApiFilter) {
        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _properties.value = MarsApi.retrofitService.getProperties(filter.value)
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }


    fun displayPropertyDetails(marsProperty: MarsProperty) {
        _navigateToSelectedProperty.value = marsProperty
    }


    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }


    fun updateFilter(filter: MarsApiFilter) {
        getMarsRealEstateProperties(filter)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}