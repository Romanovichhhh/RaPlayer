package com.example.raplayer.data.models

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import com.example.raplayer.R
import com.example.raplayer.data.SongInfo
import com.masoudss.lib.SeekBarOnProgressChanged
import com.masoudss.lib.WaveformSeekBar
import kotlinx.android.synthetic.main.fragment_tracks_tab.view.*
import kotlinx.android.synthetic.main.list_item.view.*
import java.lang.Exception
import kotlin.math.ceil
import android.view.animation.TranslateAnimation

import android.view.animation.Animation
import android.os.Handler
import com.example.raplayer.MainActivity
import kotlinx.android.synthetic.main.fragment_track.view.*
import kotlinx.android.synthetic.main.fragment_tracks_tab.view.backButton
import kotlinx.android.synthetic.main.fragment_tracks_tab.view.fullSongTimeText
import kotlinx.android.synthetic.main.fragment_tracks_tab.view.nextButton
import kotlinx.android.synthetic.main.fragment_tracks_tab.view.playButton
import kotlinx.android.synthetic.main.fragment_tracks_tab.view.prevButton
import kotlinx.android.synthetic.main.fragment_tracks_tab.view.songNameText
import kotlinx.android.synthetic.main.fragment_tracks_tab.view.waveformSeekBar
import java.util.logging.LogRecord

import java.util.*


class Model(context: Context) {

    var mp : MediaPlayer? = null
    lateinit var waveformView: WaveformSeekBar
    //private lateinit var runnable: Runnable




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
                Toast.makeText(context, "Email введён некорректно", Toast.LENGTH_SHORT).show() //TODO можно убрать контекст из объявления
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


    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    fun playTrack(itemView: View, currentSong : SongInfo, fragment : View, mySongList : ArrayList<SongInfo>) {

        var song : SongInfo = currentSong
        var waveformView: WaveformSeekBar = fragment.waveformSeekBar

        if (mp?.isPlaying == true) {
            mp!!.stop()

            itemView.playBtn.background = itemView.resources.getDrawable(R.drawable.ic_play, null)
            fragment.bottomPanel.visibility = View.GONE


        } else {

            mp = MediaPlayer()

            try {

                mp?.setDataSource(song.URL)
                mp?.prepare()
                mp?.start()
                itemView.playBtn.background =
                    itemView.resources.getDrawable(R.drawable.ic_pause, null)
                fragment.bottomPanel.visibility = View.VISIBLE

                fragment.presentTrack.hint = song.Title

                waveformView.maxProgress = (mp!!.duration/1000).toFloat()


//                val timer = Timer()
//                timer.scheduleAtFixedRate(object : TimerTask() {
//                    override fun run() {
//                        waveformView.progress = mp!!.currentPosition.toFloat()
//                    }
//                }, 0, 1000)


            } catch (excep: Exception) {
                throw excep
            }

            fragment.pauseBtn.setOnClickListener {
                mp?.stop()
                fragment.bottomPanel.visibility = View.GONE
            }

//            mp!!.setOnCompletionListener {
//                var currentSongIndex = mySongList.indexOf(song)
//                if (currentSongIndex >= 0) {
//
//                    mp!!.stop()
//                    mp!!.reset()
//                    if (mySongList.size - 1 == currentSongIndex) {
//                        currentSongIndex = 0
//                        mp!!.setDataSource(mySongList[currentSongIndex].URL)
//
//                    }
//                    else {
//                        currentSongIndex += 1
//                        mp!!.setDataSource(mySongList[currentSongIndex].URL)
//
//                    }
//                    song = mySongList[currentSongIndex]
//                    fragment.songNameText.text = mySongList[currentSongIndex].Title
//                    mySongList[currentSongIndex].URL?.let { it1 -> waveformView.setSampleFrom(it1) }
//                    mp!!.prepare()
//                    mp!!.start()
//                    fragment.fullSongTimeText.text = getCorrectDuaration(mp!!.duration.toLong())
//                    fragment.playListCount.text = mySongList.indexOf(song).toString() + "/" + mySongList.size.toString()
//                }
//            }


            fragment.presentTrack.setOnClickListener {
                val trackView = fragment.trackView
                if(trackView.visibility == View.GONE) {

                    trackView.visibility = View.VISIBLE;
                    fragment.playButton.background =
                        itemView.resources.getDrawable(R.drawable.ic_pause, null)

                    val animate = TranslateAnimation(
                        0F,  // fromXDelta
                        0F,  // toXDelta
                        trackView.getHeight().toFloat(),  // fromYDelta
                        0F
                    ) // toYDelta

                    animate.duration = 200
                    animate.fillAfter = true
                    trackView.startAnimation(animate)
                }

                fragment.playListCount.text = mySongList.indexOf(song).toString() + "/" + mySongList.size.toString()


                fragment.songNameText.text = song.Title

                if(mp != null) {
                    song.URL?.let { it1 -> waveformView.setSampleFrom(it1) }






                    waveformView.onProgressChanged = object : SeekBarOnProgressChanged {
                        override fun onProgressChanged(
                            waveformSeekBar: WaveformSeekBar,
                            progress: Float,
                            fromUser: Boolean
                        ) {
                            if (fromUser) {
                                //mp!!.seekTo()
                                mp!!.seekTo((progress*1000).toInt())
                                waveformView.progress = progress
                            }
                        }
                    }
                }


                fragment.fullSongTimeText.text = getCorrectDuaration(mp!!.duration.toLong())


                fragment.nextButton.setOnClickListener {
                    if (mp != null) {
                        var currentSongIndex = mySongList.indexOf(song)
                        if (currentSongIndex >= 0) {

                            mp!!.stop()
                            mp!!.reset()
                            if (mySongList.size - 1 == currentSongIndex) {
                                currentSongIndex = 0
                                mp!!.setDataSource(mySongList[currentSongIndex].URL)

                            }
                            else {
                                currentSongIndex += 1
                                mp!!.setDataSource(mySongList[currentSongIndex].URL)

                                }
                            song = mySongList[currentSongIndex]
                            fragment.songNameText.text = mySongList[currentSongIndex].Title
                            mySongList[currentSongIndex].URL?.let { it1 -> waveformView.setSampleFrom(it1) }
                            mp!!.prepare()
                            mp!!.start()
                            fragment.fullSongTimeText.text = getCorrectDuaration(mp!!.duration.toLong())
                            fragment.playListCount.text = mySongList.indexOf(song).toString() + "/" + mySongList.size.toString()
                        }


                    }
                }

                fragment.prevButton.setOnClickListener {

                    if (mp != null) {

                        var currentSongIndex = mySongList.indexOf(song)
                        if (currentSongIndex >= 0) {

                            mp!!.stop()
                            mp!!.reset()
                            //if(fragment.shuffleButton.background == fragment.resources.getDrawable(R.drawable.ic_shuffle_off)) {
                                if (currentSongIndex == 0) {
                                    currentSongIndex = mySongList.size - 1
                                    mp!!.setDataSource(mySongList[currentSongIndex].URL)

                                } else {
                                    currentSongIndex -= 1
                                    mp!!.setDataSource(mySongList[currentSongIndex + 1].URL)

                                }

                            song = mySongList[currentSongIndex]
                       // }
//                        else {
//                            song = mySongList[(0 until mySongList.size).random()]
//                        }
                            fragment.songNameText.text = mySongList[currentSongIndex].Title
                            mySongList[currentSongIndex].URL?.let { it1 -> waveformView.setSampleFrom(it1) }
                            mp!!.prepare()
                            mp!!.start()
                            fragment.fullSongTimeText.text = getCorrectDuaration(mp!!.duration.toLong())
                            fragment.playListCount.text = mySongList.indexOf(song).toString() + "/" + mySongList.size.toString()
                        }


                    }
                }

//                fragment.shuffleButton.setOnClickListener {
//                    if (fragment.shuffleButton.background == fragment.resources.getDrawable(R.drawable.ic_shuffle_off))
//                        //fragment.shuffleButton.background = fragment.shuffleButton.resources.getDrawable(R.drawable.ic_shuffle_on)
//                    else
//                        fragment.shuffleButton.background = fragment.shuffleButton.resources.getDrawable(R.drawable.ic_shuffle_off)
//                }

                fragment.backButton.setOnClickListener {
                    if(trackView.visibility == View.VISIBLE){
                        trackView.visibility = View.GONE
                    val animate = TranslateAnimation(
                        0F,  // fromXDelta
                        0F,  // toXDelta
                        0F,  // fromYDelta
                        trackView.getHeight().toFloat()
                    ) // toYDelta

                    animate.duration = 200
                    animate.fillAfter = true
                    trackView.startAnimation(animate)
                    }
                }

                fragment.playButton.setOnClickListener {
                    if (mp?.isPlaying == true) {
                        fragment.playButton.background = null
                        fragment.playButton.background = fragment.playButton.resources.getDrawable(R.drawable.ic_play)
                        mp!!.stop()
                    } else {

                        mp = MediaPlayer()

                        try {

                            mp?.setDataSource(song.URL)
                            mp?.prepare()
                            mp?.start()
                            fragment.playButton.background =
                                fragment.resources.getDrawable(R.drawable.ic_pause, null)


                        } catch (excep: Exception) {
                            throw excep
                        }
                    }
                }
            }
        }
    }



    fun getCorrectDuaration(wrongDuaration: Long) : String {



        var minutes : String = (wrongDuaration/ 1000 / 60).toString()
        var seconds : String = (ceil((wrongDuaration/ 1000 % 60).toDouble()).toInt()).toString()
        return "$minutes:$seconds"
    }

























}