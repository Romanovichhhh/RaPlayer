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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.raplayer.R
import com.example.raplayer.data.Model
import com.example.raplayer.data.SharedPrefs
import com.example.raplayer.data.User
import com.example.raplayer.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_login_tab.view.*
import kotlinx.android.synthetic.main.fragment_login_tab.view.enterEmail
import kotlinx.android.synthetic.main.fragment_login_tab.view.enterPassword
import kotlinx.android.synthetic.main.fragment_reg_tab.view.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
private lateinit var mUserViewModel : UserViewModel
private lateinit var sharedPreferences: SharedPreferences
private lateinit var editor: SharedPreferences.Editor


class loginTabFragment : Fragment() {


    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_login_tab, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val sharedPrefs = SharedPrefs(requireContext())

        view.loginButton.setOnClickListener {

            val enteredEmail : String = view?.enterEmail?.text.toString()
            val enteredPassword : String = view?.enterPassword?.text.toString()
            val model = Model(requireContext())
            var userExists : Int;

            if(model.dataIsValid(enteredEmail, enteredPassword, requireContext())) {
                lifecycleScope.launch {
                    userExists = mUserViewModel.checkUser(enteredEmail,enteredPassword)

                    if(userExists > 0) {
                        Toast.makeText(requireActivity(), "Вы успешно вошли", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_enterFragment_to_mainMenuFragment)
                    }
                    else Toast.makeText(requireActivity(), "Такого пользователя не существует", Toast.LENGTH_SHORT).show()
                }
            }


        }



        return view
    }

//    @InternalCoroutinesApi
//    private suspend fun checkUserInDatabase(view: View?) {
//        val enteredEmail : String = view?.enterEmail?.text.toString()
//        val enteredPassword : String = view?.enterPassword?.text.toString()
//        val sharedPrefs = SharedPrefs(requireContext())
//
//        var userExists : Int;
//
//        lifecycleScope.launch {
//            userExists = mUserViewModel.checkUser(enteredEmail,enteredPassword)
//        }
//
//        if(userExists > 0) {
//            sharedPrefs.saveSession(requireContext())
//            Toast.makeText(requireActivity(), "Вы успешно вошли", Toast.LENGTH_SHORT).show()
//        }
//        else {
//            Toast.makeText(requireActivity(), "Такого пользователя не существует", Toast.LENGTH_SHORT).show()
//        }
//    }

//    fun userExists(result: Int) : Boolean{
//        return result > 0
//    }


}

