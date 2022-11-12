package com.example.roomies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.roomies.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth


private lateinit var binding: ActivitySignInBinding
private lateinit var firebaseAuth: FirebaseAuth

/**
 * A simple [Fragment] subclass.
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivitySignInBinding.inflate(layoutInflater)
        activity?.setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()
        binding.textView.setOnClickListener {
            //val intent = Intent(this, SignUpActivity::class.java)
            //startActivity(intent)
            //go to sign up fragment
        }

        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        //val intent = context?.Intent(this, MainActivity::class.java)
                        //startActivity(intent)
                        //go to amazon fragment
                        //val fragmentManager = requireActivity().supportFragmentManager

                       // val fragment4: Fragment = AmazonFragment()
                        //val fragment5: Fragment = SignInFragment()
                      //  R.id.homeButton -> fragment = fragment5
                       // R.id.amazonButton -> fragment = fragment4


                       // fragmentManager.beginTransaction().replace(fragment5, fragment4).commit()


                    } else {
                        //Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                //Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }


    companion object {
        fun newInstance(): SignInFragment {
            return SignInFragment()
        }

    }
}