package br.ifsp.moviesmanager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.ifsp.moviesmanager.R
import br.ifsp.moviesmanager.adapter.MovieAdapter
import br.ifsp.moviesmanager.data.Movie
import br.ifsp.moviesmanager.databinding.FragmentListMoviesBinding
import br.ifsp.moviesmanager.viewmodel.MovieViewModel

class ListMoviesFragment : Fragment() {

    private var _binding: FragmentListMoviesBinding? = null
    private val binding get() = _binding!!
    lateinit var movieAdapter: MovieAdapter
    lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_listMoviesFragment_to_cadastroFragment)
        }

        configureRecyclerView()

        return binding.root
    }

    private fun configureRecyclerView() {
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        viewModel.allMovies.observe(viewLifecycleOwner) { list ->
            list?.let {
                movieAdapter.updateList(list as ArrayList<Movie>)
            }
        }
        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter
    }
}