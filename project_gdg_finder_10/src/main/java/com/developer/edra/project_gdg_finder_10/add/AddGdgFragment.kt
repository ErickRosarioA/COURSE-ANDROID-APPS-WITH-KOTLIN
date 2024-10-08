package com.developer.edra.project_gdg_finder_10.add


import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.developer.edra.project_gdg_finder_10.R
import com.developer.edra.project_gdg_finder_10.databinding.AddGdgFragmentBinding

import com.google.android.material.snackbar.Snackbar

class AddGdgFragment : Fragment() {

    private val viewModel: AddGdgViewModel by lazy {
        ViewModelProvider(this).get(AddGdgViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = AddGdgFragmentBinding.inflate(inflater)


        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        viewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.application_submitted),
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.doneShowingSnackbar()

                binding.button.contentDescription=getString(R.string.submitted)
                binding.button.text=getString(R.string.done)
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

}