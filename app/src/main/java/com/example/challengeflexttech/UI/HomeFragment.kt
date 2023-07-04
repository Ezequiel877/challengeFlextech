package com.example.challengeflexttech.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.challengeflexttech.R
import com.example.challengeflexttech.databinding.FragmentHomeBinding
import com.example.challengeflexttech.domain.CatRemoteDataSource
import com.example.challengeflexttech.model.Cat
import com.example.challengeflexttech.model.HomeFactory
import com.example.challengeflexttech.model.HomeViewModel
import com.example.challengeflexttech.repository.RepositoryImpl
import com.example.challengeflexttech.repository.Retrofit
import com.example.challengeflexttech.utils.CategoriesAdapter
import com.example.challengeflexttech.utils.getStatus


class HomeFragment : Fragment(R.layout.fragment_home), CategoriesAdapter.onClick {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by activityViewModels<HomeViewModel> {
        HomeFactory(RepositoryImpl(CatRemoteDataSource(Retrofit.retrofit)))
    }
    private lateinit var listCat: List<Cat>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        initRecycler()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.hide()

        initRecycler()

    }

    private fun initRecycler() {
        viewModel.getCats().observe(viewLifecycleOwner, Observer {
            when (it) {
                is getStatus.Loading -> {
                    Log.d("Loading", "onViewCreated: ${it}")

                }

                is getStatus.Succes -> {
                    listCat = it.data
                    binding.patsRecyclerView.adapter = CategoriesAdapter(listCat, this)
                    binding.patsEdSearch.addTextChangedListener { textInput ->
                        if (textInput.isNullOrBlank()) {
                            binding.patsRecyclerView.adapter = CategoriesAdapter(listCat, this)
                        } else {
                            listCat = it.data.filter { cat ->
                                cat.id.contains(textInput.toString())
                            }
                            binding.patsRecyclerView.adapter = CategoriesAdapter(listCat, this)
                        }
                        Log.d("Succes", "onViewCreated: ${it.data}")

                    }

                }

                is getStatus.Failure -> {

                    Log.d("TAGREFERENCEHOME", "onViewCreated: ${it.exception}")
                }


            }

        })
    }

    override fun onItemSelected(item: Cat) {
        val navegation =
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(item.url, item.id)
        findNavController().navigate(navegation)

    }



}