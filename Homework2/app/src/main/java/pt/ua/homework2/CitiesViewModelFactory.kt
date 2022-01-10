package pt.ua.homework2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class CitiesViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CitiesViewModel::class.java)){
            return CitiesViewModel() as T
        }
        throw IllegalArgumentException("Unkonw ViewModell class")
    }
}