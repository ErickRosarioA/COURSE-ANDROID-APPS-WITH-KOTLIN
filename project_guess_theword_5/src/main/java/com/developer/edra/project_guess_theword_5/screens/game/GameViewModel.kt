package com.developer.edra.project_guess_theword_5.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    init {
        Log.i("GameViewModel", "GameViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }
}