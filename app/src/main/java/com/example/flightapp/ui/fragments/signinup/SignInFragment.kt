package com.example.flightapp.ui.fragments.signinup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentSignInBinding
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var callbackManager: CallbackManager
    private lateinit var buttonFacebookLogin: LoginButton

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(
                    requireContext(), "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater)

        //Google
        mAuth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        //Facebook
        callbackManager = CallbackManager.Factory.create()
        buttonFacebookLogin = binding.facebookSignIn
        buttonFacebookLogin.setReadPermissions("email", "public_profile")

        buttonFacebookLogin.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    handleFacebookAccessToken(loginResult.accessToken)
                }

                override fun onCancel() {
                    Log.d("TAG", "facebook:onCancel")
                }

                override fun onError(error: FacebookException) {
                    Log.d("TAG", "facebook:onError", error)
                }
            },
        )

        //Check user fill all fields for email signin
        binding.btnSignIn.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                signInWithEmail(email, password)
            } else {
                Toast.makeText(
                    requireContext(),
                    "please fill email and password fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        //Change ui to signup
        binding.txtSignUp.setOnClickListener {
            findNavController().navigate(R.id.toSignUpFragment)
        }

        setVisibility()
        signInWithGoogle()

        return binding.root
    }


    private fun setVisibility() {
        binding.txtFirstName.visibility = View.GONE
        binding.txtLastName.visibility = View.GONE
        binding.edtFirstName.visibility = View.GONE
        binding.edtLastName.visibility = View.GONE
        binding.arrowBack.visibility = View.INVISIBLE
    }

    private fun signInWithEmail(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        "Signed in as ${binding.edtEmail.text}",
                        Toast.LENGTH_SHORT
                    ).show()
                    onSignInSuccess()
                } else {
                    Toast.makeText(
                        requireContext(), "Authentication failed", Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun onSignInSuccess() {
        findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
    }

    private fun signInWithGoogle() {
        val signInButton = binding.googleSignIn
        signInButton.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startForResult.launch(signInIntent)
        }
    }

    //Facebook
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                val user = mAuth.currentUser
                Toast.makeText(
                    requireContext(), "Signed in as ${user?.displayName}", Toast.LENGTH_SHORT
                ).show()
                onSignInSuccess()
            } else {
                Toast.makeText(
                    requireContext(), "Authentication failed", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth.signInWithCredential(credential).addOnCompleteListener((requireActivity())) { task ->
            if (task.isSuccessful) {
                val user = mAuth.currentUser
                Toast.makeText(
                    requireContext(), "Signed in as ${user?.displayName}", Toast.LENGTH_SHORT
                ).show()
                onSignInSuccess()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Authentication failed.",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }
    }


}