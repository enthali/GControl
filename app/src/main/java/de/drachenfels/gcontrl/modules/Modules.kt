/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.drachenfels.gcontrl.modules

import android.content.SharedPreferences
import android.location.Location
import androidx.lifecycle.MutableLiveData

/**
 * Returns the `location` object as a human readable string.
 */
fun Location?.toText(): String {
    return if (this != null) {
        "($latitude, $longitude)"
    } else {
        "Unknown location"
    }
}


/**
 * the application wide shared preferences will be available during ControlFragment onCreate
 */
lateinit var sharedPreferences: SharedPreferences


/**
 * The object contains shared resources used between the ControlView and the Location Service
 */
internal object SharedLocationResources {

    var currentLocation : Location = Location("initialLocation")

    private var privateLocationUpdate = MutableLiveData(0)
    var locationUpdate: MutableLiveData<Int>
        get() = privateLocationUpdate
        set(value) {
            privateLocationUpdate = value
        }

    // distance live data
    private var privateDistanceToHome = MutableLiveData(0f)

    /**
     * any changes to the calculation of the distance to home can be observed applicatoin wide
     */
    var distanceToHome: MutableLiveData<Float>
        get() = privateDistanceToHome
        set(value) {
            privateDistanceToHome = value
        }
}