package com.developer.edra.project_sleep_tracker_6.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.developer.edra.project_sleep_tracker_6.database.SleepDatabaseDao
import com.developer.edra.project_sleep_tracker_6.database.SleepNight
import com.developer.edra.project_sleep_tracker_6.formatNights
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel for SleepTrackerFragment.
 */
class SleepTrackerViewModel(
    val database: SleepDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var tonight = MutableLiveData<SleepNight?>()

    private val nights = database.getAllNights()

    // Convertir las noches a formato de cadena para mostrar
    val nightsString = nights.map { nights ->
        formatNights(nights, application.resources)
    }

    // LiveData para navegar al fragmento de SleepQuality
    private val _navigateToSleepQuality = MutableLiveData<SleepNight?>()

    val navigateToSleepQuality: LiveData<SleepNight?>
        get() = _navigateToSleepQuality

    // Método para limpiar la navegación una vez completada
    fun doneNavigating() {
        _navigateToSleepQuality.value = null
    }

    // Inicializa la noche actual al iniciar el ViewModel
    init {
        initializeTonight()
    }

    // Inicializa la noche actual con coroutines en el hilo IO
    private fun initializeTonight() {
        viewModelScope.launch {
            tonight.value = getTonightFromDatabase()
        }
    }

    // Obtiene la noche actual de la base de datos en un hilo de fondo
    private suspend fun getTonightFromDatabase(): SleepNight? {
        return withContext(Dispatchers.IO) {
            var night = database.getTonight()
            if (night?.endTimeMilli != night?.startTimeMilli) {
                night = null
            }
            night
        }
    }

    // Limpia la base de datos
    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    // Actualiza una noche en la base de datos
    private suspend fun update(night: SleepNight) {
        withContext(Dispatchers.IO) {
            database.update(night)
        }
    }

    // Inserta una nueva noche en la base de datos
    private suspend fun insert(night: SleepNight) {
        withContext(Dispatchers.IO) {
            database.insert(night)
        }
    }

    // Ejecutado cuando el botón START es presionado
    fun onStartTracking() {
        viewModelScope.launch {
            val newNight = SleepNight()
            insert(newNight)
            tonight.value = getTonightFromDatabase()
        }
    }

    // Ejecutado cuando el botón STOP es presionado
    fun onStopTracking() {
        viewModelScope.launch {
            val oldNight = tonight.value ?: return@launch
            oldNight.endTimeMilli = System.currentTimeMillis()
            update(oldNight)

            // Navega al fragmento de SleepQuality
            _navigateToSleepQuality.value = oldNight
        }
    }

    // Ejecutado cuando el botón CLEAR es presionado
    fun onClear() {
        viewModelScope.launch {
            // Limpia la tabla de la base de datos
            clear()
            // Limpia la noche actual ya que no está más en la base de datos
            tonight.value = null
        }
    }
}