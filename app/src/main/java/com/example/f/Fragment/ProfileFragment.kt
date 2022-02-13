package com.example.f.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.f.Activity.SignInActivity
import com.example.f.R
import com.example.f.databinding.FragmentProfileBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var googleAuth: GoogleSignInClient

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        setupUI()
        setupGoogleAuth()

        return binding.root
    }

    private fun setupUI() {

        val acc = GoogleSignIn.getLastSignedInAccount(requireContext())
        if(acc != null) {
            binding.profileEmail.text = acc.email
            binding.profileName.text = acc.displayName
            Picasso.get()
                .load(acc.photoUrl)
                .placeholder(R.drawable.ic_avatar_profile)
                .into(binding.profilePic)
        }
    }

    private fun setupGoogleAuth() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleAuth = GoogleSignIn.getClient(requireContext(), gso)
        binding.logout.setOnClickListener {
            googleAuth.signOut().addOnCompleteListener {
                val i = Intent(requireContext(), SignInActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)
                activity?.finish()
            }
        }

    }
}