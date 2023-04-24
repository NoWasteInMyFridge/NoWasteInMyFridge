package com.develop.nowasteinmyfridge.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.develop.nowasteinmyfridge.R
import com.develop.nowasteinmyfridge.adapter.NoWasteInMyFrideAdapter
import com.develop.nowasteinmyfridge.databinding.FragmentHomeBinding
import com.develop.nowasteinmyfridge.viewmodels.NoWasteInMyFrideViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var noWasteInMyFrideAdapter: NoWasteInMyFrideAdapter

    private val noWasteInMyFrideViewModel: NoWasteInMyFrideViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = this@HomeFragment
            rvFoodItem.apply {
                adapter = noWasteInMyFrideAdapter
                layoutManager = LinearLayoutManager(
                    this.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )

            }
        }

        noWasteInMyFrideViewModel.meal.observe(viewLifecycleOwner){
            meal -> noWasteInMyFrideAdapter.submitList(meal)
        }
    }

}