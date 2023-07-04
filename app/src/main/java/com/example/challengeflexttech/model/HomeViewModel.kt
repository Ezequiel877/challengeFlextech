package com.example.challengeflexttech.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.challengeflexttech.repository.Repository
import com.example.challengeflexttech.utils.getStatus
import kotlinx.coroutines.Dispatchers

/**
 * inyeccion de depencias manual
 * */
class HomeViewModel(private val repo: Repository) : ViewModel() {

    /**
     * patron observer
     * */
    fun getCats() = liveData(Dispatchers.IO) {
        emit(getStatus.Loading())
        try {
            emit(getStatus.Succes(repo.getCat()))
        } catch (e: Exception) {
            emit(getStatus.Failure(e))
        }
    }

}
/**
 * creacion de factory para poder pasar este tipo de parametros por constructor
 *
 * */
class HomeFactory(private val repo: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repository::class.java).newInstance(repo)
    }
}
