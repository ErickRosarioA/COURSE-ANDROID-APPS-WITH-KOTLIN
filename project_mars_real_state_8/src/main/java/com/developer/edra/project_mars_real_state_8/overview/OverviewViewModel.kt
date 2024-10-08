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
import com.developer.edra.project_mars_real_state_8.network.MarsApi
import com.developer.edra.project_mars_real_state_8.network.MarsProperty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OverviewViewModel : ViewModel() {


    private val _response = MutableLiveData<String>()


    val response: LiveData<String>
        get() = _response


    init {
        getMarsRealEstateProperties()
    }


    private fun getMarsRealEstateProperties() {
        MarsApi.retrofitService.getProperties().enqueue( object: Callback<List<MarsProperty>> {
            override fun onFailure(call: Call<List<MarsProperty>>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<List<MarsProperty>>, response: Response<List<MarsProperty>>) {
                _response.value = "Success: ${response.body()?.size} Mars properties retrieved"
            }
        })
    }
}