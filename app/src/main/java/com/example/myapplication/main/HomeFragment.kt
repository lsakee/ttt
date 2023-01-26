package com.example.myapplication.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.main.adapter.RecentRecyclerViewAdapter
import com.example.myapplication.main.adapter.SubRecyclerViewAdapter
import com.example.myapplication.main.network.RetrofitClient
import com.example.myapplication.main.util.CenterZoomLinearLayoutManager
import com.skydoves.sandwich.onSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recentRvAdapter = RecentRecyclerViewAdapter()
        binding.rvRecent.layoutManager = CenterZoomLinearLayoutManager(requireContext())
        binding.rvRecent.adapter = recentRvAdapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvRecent)

        val subRvAdapter = SubRecyclerViewAdapter()
        binding.rvSub.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSub.adapter = subRvAdapter

        binding.stickyNestedScrollView.run {
            header = binding.clHeader
            stickListener = { _ ->
                setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
                binding.clHeader.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
                binding.tvSubtitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                binding.tvSubtitle2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            }
            freeListener = { _ ->
                setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black))
                binding.clHeader.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black))
                binding.tvSubtitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                binding.tvSubtitle2.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
        }

        binding.btnKakaotalk.setOnClickListener {
            binding.viewBottomSheetShadow.visibility = View.VISIBLE

            binding.cardViewBottomSheet.visibility = View.VISIBLE
            binding.cardViewBottomSheet.startAnimation(AnimationUtils.loadAnimation(
                context,
                R.anim.slide_up))
        }

        binding.tvBottomSheetClose.setOnClickListener {
            binding.cardViewBottomSheet.startAnimation(AnimationUtils.loadAnimation(
                context,
                R.anim.slide_down))
            binding.cardViewBottomSheet.visibility = View.GONE
            binding.viewBottomSheetShadow.visibility = View.GONE
        }

        CoroutineScope(Dispatchers.IO).launch {
            RetrofitClient.apiService.fetchCategories().onSuccess {
                val data = this.data.data.categories
                CoroutineScope(Main).launch {
                    recentRvAdapter.submitList(data)
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            RetrofitClient.apiService.fetchCategories().onSuccess {
                val data = this.data.data.categories
                CoroutineScope(Main).launch {
                    subRvAdapter.submitList(data)
                }
            }
        }
    }
}