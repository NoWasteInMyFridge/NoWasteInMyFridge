package com.develop.nowasteinmyfridge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.develop.nowasteinmyfridge.adapter.loadIconUrl
import com.develop.nowasteinmyfridge.databinding.FragmentDetailFoodItemBinding
import com.develop.nowasteinmyfridge.viewmodels.NoWasteInMyFrideViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFoodItemFragment : Fragment(R.layout.fragment_detail_food_item) {
    lateinit var binding: FragmentDetailFoodItemBinding
    val args:DetailFoodItemFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailFoodItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val meal = args.meal
        binding.tvNameFoodItem.text = meal.strMeal
        binding.ivFoodItem.apply {
            val imageLoader = ImageLoader.Builder(this.context)
                .build()


            val request = ImageRequest.Builder(this.context)
                .crossfade(true)
                .crossfade(500)
                .placeholder(R.drawable.loading_placeholder)
                .error(R.drawable.ic_broken_image)
                .data(meal.strMealThumb)
                .target(this)
                .build()

            imageLoader.enqueue(request)
        }


    }

}