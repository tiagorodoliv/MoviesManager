package br.ifsp.moviesmanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.ifsp.moviesmanager.data.Movie
import br.ifsp.moviesmanager.databinding.MovieCelulaBinding

class MovieAdapter():
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private lateinit var binding: MovieCelulaBinding
    var moviesLista = ArrayList<Movie>()
    var moviesListaFilterable = ArrayList<Movie>()
    fun updateList(newList: ArrayList<Movie> ){
        moviesLista = newList
        moviesListaFilterable = moviesLista
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        binding = MovieCelulaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.nomeVH.text = moviesLista[position].nome
        holder.genderVH.text = moviesLista[position].gender
        holder.durationVH.text = moviesLista[position].duration
        holder.noteVH.text = moviesLista[position].note
    }
    override fun getItemCount(): Int {
        return moviesLista.size
    }
    inner class MovieViewHolder(view:MovieCelulaBinding): RecyclerView.ViewHolder(view.root)
    {
        val nomeVH = view.name
        val genderVH = view.gender
        val durationVH = view.duration
        val noteVH = view.note
    }
}