package com.example.myapplication.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProfileBinding
import com.example.myapplication.main.network.RetrofitClient
import com.example.myapplication.main.util.FormDataUtil
import com.example.myapplication.main.util.ImageUtil
import com.nguyenhoanglam.imagepicker.model.ImagePickerConfig
import com.nguyenhoanglam.imagepicker.ui.imagepicker.registerImagePicker
import com.skydoves.sandwich.onSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.text.Normalizer.Form

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val launcher = registerImagePicker { images ->
        if(images.isNotEmpty()){
            val sampleImage = images[0]
            Glide.with(requireActivity())
                .load(sampleImage.uri)
                .into(binding.iv)
            CoroutineScope(IO).launch {
                RetrofitClient.apiService.uploadFile(
                    FormDataUtil.getBody("type","plubbing-main"),
                    FormDataUtil.getImageBody("files",ImageUtil(requireActivity()).getFileFromUri(sampleImage.uri))
                ).onSuccess {
                    Log.d("Retrofit2","성공 ${data}")
                }
            }
        }
    }

    private val config = ImagePickerConfig(
        isShowCamera = true
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.iv.setOnClickListener {
            launcher.launch(config)
        }
    }


}