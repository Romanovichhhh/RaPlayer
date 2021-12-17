//package com.example.raplayer
package com.example.raplayer.gateTabFragments


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.raplayer.R
import com.example.raplayer.data.Model
import com.example.raplayer.data.SharedPrefs
import com.example.raplayer.data.User
import com.example.raplayer.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_reg_tab.view.*
import kotlinx.coroutines.InternalCoroutinesApi


private lateinit var sharedPreferences: SharedPreferences
private lateinit var editor: SharedPreferences.Editor
@InternalCoroutinesApi
private lateinit var mUserViewModel : UserViewModel

class regTabFragment : Fragment() {




    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_reg_tab, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)




        view.registerButton.setOnClickListener {

            val enteredEmail : String = view?.enterEmail?.text.toString()
            val enteredUsername : String = view?.enterUsername?.text.toString()
            val enteredPassword : String = view?.enterPassword?.text.toString()
            val repeatedPassword : String = view?.repeatPassword?.text.toString()
            val model = Model(requireContext())

            if(model.dataIsValid(enteredEmail, enteredUsername, enteredPassword, repeatedPassword, requireContext())) {
            insertDataToDatabase(view)
            findNavController().navigate(R.id.action_enterFragment_to_mainMenuFragment)
            }
        }


        return view
    }

    @InternalCoroutinesApi
    private fun insertDataToDatabase(view: View?) {
        val enteredEmail : String = view?.enterEmail?.text.toString()
        val enteredUsername : String = view?.enterUsername?.text.toString()
        val enteredPassword : String = view?.enterPassword?.text.toString()
        val sharedPrefs = SharedPrefs(requireContext())

            val user = User(0,enteredUsername, enteredEmail!!, enteredPassword!!)
            mUserViewModel.addUser(user)
            sharedPrefs.saveSession(requireContext())
            Toast.makeText(requireActivity(), "Вы успешно зарегестрировались", Toast.LENGTH_SHORT).show()


    }




}