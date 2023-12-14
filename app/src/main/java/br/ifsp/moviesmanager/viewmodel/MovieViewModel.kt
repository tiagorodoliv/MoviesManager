package br.ifsp.moviesmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.ifsp.moviesmanager.data.Movie
import br.ifsp.moviesmanager.data.MovieDatabase
import br.ifsp.moviesmanager.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MovieRepository
    lateinit var movie:LiveData<Movie>

    init {
        val dao = MovieDatabase.getDatabase(application).movieDAO()
        repository = MovieRepository(dao)
    }

    fun  insert(movie: Movie) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(movie)
    }
}