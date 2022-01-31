package com.example.f.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.f.ViewModel.BookApiViewModel
import com.example.f.databinding.RecyclerSeeAllBinding

class SeeAllActivity : AppCompatActivity() {

    private lateinit var binding: RecyclerSeeAllBinding
    private lateinit var viewModel: BookApiViewModel

    companion object {
        const val EXTRAS_ROMANCE = "extras_romance"
        const val EXTRAS_ADVENTURE = "extras_adventure"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecyclerSeeAllBinding.inflate(layoutInflater)


        setContentView(binding.root)
    }

}