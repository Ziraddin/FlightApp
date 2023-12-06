package com.example.flightapp.ui.fragments.signinup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentSignInBinding
import com.example.flightapp.model.User
import com.example.flightapp.viewmodels.UserVm
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.userProfileChangeRequest

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mAuth: FirebaseAuth
    private lateinit var callbackManager: CallbackManager
    private lateinit var buttonFacebookLogin: LoginButton
    private lateinit var userVm: UserVm

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
        mAuth = FirebaseAuth.getInstance()
        userVm = ViewModelProvider(this)[UserVm::class.java]


        adaptLayout()
        signInWithGoogle()

        binding.btnSignIn.setOnClickListener {
            val user = User(
                firstname = binding.edtFirstName.text.toString(),
                lastname = binding.edtLastName.text.toString(),
                email = binding.edtEmail.text.toString(),
                password = binding.edtPassword.text.toString()
            )
            if (user.email!!.isNotEmpty() && user.password!!.isNotEmpty()) {
                createAccountWithEmail(user)
            } else {
                Toast.makeText(
                    requireContext(), "please fill email and password fields", Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.txtSignUp.setOnClickListener {
            findNavController().popBackStack()
        }
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

        return binding.root
    }

    private fun adaptLayout() {
        binding.btnSignIn.setText(R.string.btnSignUp)
        binding.txtAlready.setText(R.string.txtAlready)
        binding.txtSignUp.setText(R.string.btnSignUp)
        binding.txtSignIn.setText(R.string.btnSignUp)
        binding.arrowBack.apply {
            visibility = View.INVISIBLE
            isEnabled = false
        }
    }

    private fun createAccountWithEmail(user: User) {
        mAuth.createUserWithEmailAndPassword(user.email!!.toString(), user.password!!.toString())
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val userFirebase = mAuth.currentUser
                    Toast.makeText(
                        requireContext(),
                        "Signed in as ${user.firstname + " " + user.lastname}",
                        Toast.LENGTH_SHORT
                    ).show()
                    userVm.addUser(user)
                    userFirebase!!.updateProfile(userProfileChangeRequest {
                        displayName = user.firstname + " " + user.lastname
                    })
                    userVm.userLiveData.observe(viewLifecycleOwner) {
                        if (it != null) {
                            onSignInSuccess()
                        } else {
                            Toast.makeText(
                                requireContext(), "Authentication failed", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        requireContext(), "Authentication failed", Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                val user = mAuth.currentUser
                Toast.makeText(
                    requireContext(), "Signed in as ${user?.displayName}", Toast.LENGTH_SHORT
                ).show()

                userVm.addUser(
                    User(
                        firstname = user!!.displayName!!.split(" ")[0],
                        lastname = user.displayName!!.split(" ")[1],
                        email = user.email!!,
                        password = user.uid
                    )
                )
                userVm.userLiveData.observe(viewLifecycleOwner) {
                    if (it != null) {
                        onSignInSuccess()
                    } else {
                        Toast.makeText(
                            requireContext(), "Authentication failed", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Authentication failed: ${task.exception?.message}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("AuthenticationError", task.exception?.message, task.exception)
            }
        }
    }

    private fun signInWithGoogle() {
        val signInButton = binding.googleSignIn
        signInButton.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startForResult.launch(signInIntent)
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                val user = mAuth.currentUser
                Toast.makeText(
                    requireContext(), "Signed in as ${user!!.displayName}", Toast.LENGTH_SHORT
                ).show()
                userVm.addUser(
                    User(
                        firstname = user.displayName!!.split(" ")[0],
                        lastname = user.displayName!!.split(" ")[1],
                        email = user.email!!,
                        password = user.uid.toString()
                    )
                )
                userVm.userLiveData.observe(viewLifecycleOwner) {
                    if (it != null) {
                        onSignInSuccess()
                    } else {
                        Toast.makeText(
                            requireContext(), "Authentication failed", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(
                    requireContext(), "Authentication failed", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun onSignInSuccess() {
        findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
    }
}