package com.example.raplayer.data

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import com.example.raplayer.R
import com.masoudss.lib.WaveformSeekBar
import kotlinx.android.synthetic.main.fragment_tracks_tab.view.*
import kotlinx.android.synthetic.main.list_item.view.*
import java.lang.Exception

class Model(context: Context) {

    var mp : MediaPlayer? = MediaPlayer()
    lateinit  var navController: NavController


    //Функция корректности ввода
    fun dataIsValid(enteredEmail: String, enteredPassword: String, context: Context): Boolean {

        var checked: Boolean = false


        //Проверка на то, заполнены ли все поля
        if (enteredEmail.isNotEmpty() && enteredPassword.isNotEmpty()) {
            //Проверка на правильно введённый пароль
            if (isValidEmail(enteredEmail)) {
                //Проверка на нужное количество символов в пароле
                if (enteredPassword.length in 6..20) {
                    //Проверка на совпадение поля "Пароль" и "Повторите пароль"
                    checked = true
                    return checked //Если все проверки пройдены возвращаем true

                    //Блок els'ов где выводятся соответствующие сообщения об ошибке
                } else {
                    Toast.makeText(context, "Пароль введён некорректно", Toast.LENGTH_SHORT).show()
                    return checked

                }
            } else {
                Toast.makeText(context, "Email введён некорректно", Toast.LENGTH_SHORT).show()
                return checked
            }

        } else {
            Toast.makeText(context, "Не все поля заполнены", Toast.LENGTH_SHORT)
                .show()
            return checked
        }
    }


    fun dataIsValid (enteredEmail: String, enteredUsername : String, enteredPassword: String, repeatedPassword : String, context: Context) : Boolean {
        var checked : Boolean = false

        //Проверка на то, заполнены ли все поля
        if(enteredEmail.isNotEmpty() && enteredUsername.isNotEmpty() && enteredPassword.isNotEmpty() && repeatedPassword.isNotEmpty()) {
            //Проверка на правильно введённый пароль
            if (isValidEmail(enteredEmail)) {
                //Проверка на нужное количество символов в пароле
                if (enteredPassword.length in 6..20) {
                    //Проверка на совпадение поля "Пароль" и "Повторите пароль"
                    if (enteredPassword == repeatedPassword) {
                        //Проверка на пробелы в юзернейме
                        return if (!enteredUsername.contains(" ",false)) {
                            checked = true
                            checked //Если все проверки пройдены возвращаем true
                        } else {
                            Toast.makeText(context, "Имя пользователя введено некорректно", Toast.LENGTH_SHORT).show()
                            checked
                        }

                        //Блок els'ов где выводятся соответствующие сообщения об ошибке
                    } else {
                        Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                        return checked
                    }
                } else {
                    Toast.makeText(context, "Пароль введён некорректно", Toast.LENGTH_SHORT).show()
                    return checked
                }
            } else {
                Toast.makeText(context, "Email введён некорректно", Toast.LENGTH_SHORT).show()
                return checked
            }
        }
        else {
            Toast.makeText(context, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            return checked
        }
    }

    private fun isValidEmail(target: CharSequence): Boolean { //Функция проверяющая корректность введённого мыла
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    fun playTrack(itemView: View, song : SongInfo, fragment : View, navController: NavController) {


        if (mp?.isPlaying == true)
        {
            mp!!.stop()
            //mp!!.release()
            itemView.playBtn.background = itemView.resources.getDrawable(R.drawable.ic_play, null)


        } else {

            mp = MediaPlayer()

            try {

                mp?.setDataSource(song.URL)
                mp?.prepare()
                mp?.start()
                itemView.playBtn.background = itemView.resources.getDrawable(R.drawable.ic_pause, null)
                fragment.bottomPanel.visibility = View.VISIBLE
                fragment.presentTrack.hint = song.Title
            } catch (excep: Exception) {
                    throw excep
            }

            fragment.pauseBtn.setOnClickListener {
                mp?.stop()
                fragment.bottomPanel.visibility = View.INVISIBLE
            }

            fragment.presentTrack.setOnClickListener {

                val bundle = Bundle()
                bundle.putString("songTitle", song.Title)
                bundle.putString("URL", song.URL)

                if(mp?.isPlaying == true) {
                    bundle.putBoolean("Playing", true)
                }
                navController.navigate(R.id.action_mainMenuFragment_to_trackFragment, bundle)
            }
        }
    }

    fun BindTrackView(songInfo: SongInfo, view : View, navController: NavController) {

        var waveformView : WaveformSeekBar = view.findViewById<WaveformSeekBar>(R.id.waveformSeekBar)
        songInfo.URL?.let { waveformView.setSampleFrom(it) }


        mp?.setDataSource(songInfo.URL)
        view.findViewById<TextView>(R.id.songNameText).text = songInfo.Title

        var a = mp?.isPlaying

        view.findViewById<View>(R.id.backButton).setOnClickListener {

            val bundle = Bundle()
            bundle.putString("songTitle", songInfo.Title)
            bundle.putString("URL", songInfo.URL)
//            TODO() поменять на привязку к кнопке плей
            if(mp?.isPlaying == true) {
                bundle.putBoolean("Playing", true)
            }

            navController.navigate(R.id.action_trackFragment_to_mainMenuFragment, bundle)
        }









    }






















}