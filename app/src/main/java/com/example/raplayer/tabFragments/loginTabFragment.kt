package com.example.raplayer.tabFragments

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
import com.example.raplayer.R
import kotlinx.android.synthetic.main.fragment_login_tab.view.*


private lateinit var sharedPreferences: SharedPreferences
private lateinit var editor: SharedPreferences.Editor


class loginTabFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_login_tab, container, false)


        saveSession()



        return view
    }


    private fun saveSession() {

        sharedPreferences = requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit();
        editor.apply{putBoolean("BOOLEAN_KEY", true)}.apply()
    }

    //Функция корректности ввода
    private fun dataIsValid(view: View?): Boolean {

        var checked: Boolean = false

        val enteredEmail: String = view?.enterEmail?.text.toString()
        val enteredPassword: String = view?.enterPassword?.text.toString()


        //Проверка на то, заполнены ли все поля
        if (enteredEmail.isNotEmpty() && enteredPassword.isNotEmpty()) {
            //Проверка на правильно введённый пароль
            if (isValidEmail(enteredEmail)) {
                //Проверка на нужное количество символов в пароле
                if (enteredPassword?.length in 6..20) {
                    //Проверка на совпадение поля "Пароль" и "Повторите пароль"
                    checked = true
                    return checked //Если все проверки пройдены возвращаем true


                    //Блок els'ов где выводятся соответствующие сообщения об ошибке
                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Пароль введён некорректно",
                        Toast.LENGTH_SHORT
                    ).show()
                    return checked

                }
            } else {
                Toast.makeText(requireActivity(), "Email введён некорректно", Toast.LENGTH_SHORT)
                    .show()
                return checked
            }

        } else {
            Toast.makeText(requireActivity(), "Не все поля заполнены", Toast.LENGTH_SHORT)
                .show()
            return checked
        }

    }

    private fun isValidEmail(target: CharSequence?): Boolean { //Функция проверяющая корректность введённого мыла
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()

    }


}

