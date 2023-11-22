package com.example.flightapp.ui.fragments.signinup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentSignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mAuth: FirebaseAuth

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            onActivityResult(RC_SIGN_IN, result.resultCode, result.data)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater)
        adaptLayout()
        binding.txtSignUp.setOnClickListener {
            findNavController().popBackStack()
        }
//
//        if (FirebaseAuth.getInstance().currentUser != null) {
//            findNavController().navigate(R.id.toSignInFragment)
//        }
//        mAuth = FirebaseAuth.getInstance()
//
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//
//        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
//        val signInButton = binding.googleSignIn
//        signInButton.setOnClickListener {
//            signIn()
//        }

        return binding.root
    }

    private fun adaptLayout() {
        binding.btnSingIn.setText(R.string.btnSignUp)
        binding.txtAlready.setText(R.string.txtAlready)
        binding.txtSignUp.setText(R.string.btnSignUp)
        binding.txtSignIn.setText(R.string.btnSignUp)
        binding.arrowBack.apply {
            visibility = View.INVISIBLE
            isEnabled = false
        }
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startForResult.launch(signInIntent)
    }

    private fun onSignInSuccess() {
        findNavController().navigate(R.id.toSignInFragment)
    }

    companion object {
        const val RC_SIGN_IN = 9001
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(
                    requireContext(),
                    "Google sign in failed: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    Toast.makeText(
                        requireContext(),
                        "Signed in as ${user?.displayName}",
                        Toast.LENGTH_SHORT
                    ).show()
                    onSignInSuccess()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Authentication failed",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
    }
}