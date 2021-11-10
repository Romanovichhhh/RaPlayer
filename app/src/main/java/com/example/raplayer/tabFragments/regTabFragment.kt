//package com.example.raplayer
package com.example.raplayer


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.fragment_reg_tab.*
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.raplayer.R
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

            insertDataToDatabase(view)

        }


        return view
    }

    @InternalCoroutinesApi
    private fun insertDataToDatabase(view: View?) {
        val enteredEmail : String? = view?.enterEmail?.text.toString()
        val enteredUsername : String? = view?.enterUsername?.text.toString()
        val enteredPassword : String? = view?.enterPassword?.text.toString()
        if(dataIsValid(view)) {
            val user = User(0,enteredUsername!!, enteredEmail!!, enteredPassword!!)
            mUserViewModel.addUser(user)
            saveSession()
            Toast.makeText(requireActivity(), "Вы успешно зарегестрировались", Toast.LENGTH_SHORT).show()
            requireActivity().setContentView(R.layout.activity_main_menu)
        }

    }


    private fun saveSession() {

        sharedPreferences = requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit();
        editor.apply{putBoolean("BOOLEAN_KEY", true)}.apply()

    }

    //Проверка на правильность ввода данных
    private fun dataIsValid (view: View?) : Boolean {
        var checked : Boolean = false

        val enteredEmail : String? = view?.enterEmail?.text.toString()
        val enteredUsername : String? = view?.enterUsername?.text.toString()
        val enteredPassword : String? = view?.enterPassword?.text.toString()
        val repeatedPassword : String? = view?.repeatPassword?.text.toString()

        //Проверка на то, заполнены ли все поля
        if(enteredEmail?.isNotEmpty() == true && enteredUsername?.isNotEmpty() == true && enteredPassword?.isNotEmpty() == true && repeatedPassword?.isNotEmpty() == true) {
            //Проверка на правильно введённый пароль
            if (isValidEmail(enteredEmail)) {
                //Проверка на нужное количество символов в пароле
                if (enteredPassword?.length in 6..20) {
                    //Проверка на совпадение поля "Пароль" и "Повторите пароль"
                    if (enteredPassword == repeatedPassword) {
                        //Проверка на пробелы в юзернейме
                        return if (!enteredUsername.contains(" ",false)) {
                            checked = true
                            checked //Если все проверки пройдены возвращаем true
                        } else {
                            Toast.makeText(requireActivity(), "Имя пользователя введено некорректно", Toast.LENGTH_SHORT)
                                .show()
                            checked
                        }

                        //Блок els'ов где выводятся соответствующие сообщения об ошибке
                    } else {
                        Toast.makeText(requireActivity(), "Пароли не совпадают", Toast.LENGTH_SHORT)
                            .show()
                        return checked
                    }
                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Пароль введён некорректно",
                        Toast.LENGTH_SHORT
                    ).show()
                    return checked
                }
            } else {
                Toast.makeText(requireActivity(), "Email введён некорректно", Toast.LENGTH_SHORT).show()
                return checked
            }
        }
        else {
            Toast.makeText(requireActivity(), "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            return checked
        }
    }
    // Встроеная функция проверки емейла
    private fun isValidEmail(target: CharSequence?): Boolean { //Функция проверяющая корректность введённого мыла
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }


}