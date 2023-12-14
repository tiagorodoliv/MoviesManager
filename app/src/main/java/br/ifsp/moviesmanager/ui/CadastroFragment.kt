package br.ifsp.moviesmanager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.ifsp.moviesmanager.R
import br.ifsp.moviesmanager.data.Movie
import br.ifsp.moviesmanager.databinding.FragmentCadastroBinding
import br.ifsp.moviesmanager.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar

class CadastroFragment : Fragment() {
    private var _binding: FragmentCadastroBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCadastroBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            // Add menu items here
                menuInflater.inflate(R.menu.cadastro_menu, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_salvarFilme -> {
                        val nome = binding.commonLayout.editTextNome.text.toString()
                        val releaseYear= binding.commonLayout.editTextReleaseYear.text.toString()
                        val studio:String= binding.commonLayout.editTextStudio.text.toString()
                        val duration= binding.commonLayout.editTextDuration.text.toString()
                        val note= binding.commonLayout.editTextNote.text.toString()
                        val gender= binding.commonLayout.editTextGender.text.toString()
                        val flag = if (binding.commonLayout.checkboxFlag.isChecked) {
                            1 // Ou qualquer valor que represente "Assistido" no seu contexto
                        } else {
                            0 // Ou qualquer valor que represente "Não Assistido" no seu contexto
                        }

                        val movie = Movie(nome, releaseYear, studio, duration, flag, note, gender)
                        viewModel.insert(movie)
                        Snackbar.make(binding.root, "Filme inserido", Snackbar.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}