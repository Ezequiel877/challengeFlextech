package com.example.challengeflexttech.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.example.challengeflexttech.R
import com.example.challengeflexttech.databinding.FragmentDetailsBinding
import com.example.challengeflexttech.databinding.FragmentHomeBinding
import com.example.challengeflexttech.utils.Load
import com.example.challengeflexttech.utils.downloadPhoto
import com.example.challengeflexttech.utils.expandToFullScreen

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentDetailsBinding.bind(view)
        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.hide()
        binding.catPhoto.Load(args.url)
        binding.buttonIn.setOnClickListener {
            activity.downloadPhoto(args.url)
        }
        binding.fullImagen.setOnClickListener {
            binding.catPhoto.expandToFullScreen()

        }

    }
}