package br.ifsp.moviesmanager.repository

import br.ifsp.moviesmanager.data.Movie
import br.ifsp.moviesmanager.data.MovieDAO

class MovieRepository(private val movieDAO: MovieDAO) {
    suspend fun insert(movie:Movie){
        movieDAO.insert(movie)
    }
}