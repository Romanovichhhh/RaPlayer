package com.example.raplayer.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    /*suspend*/ fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE email = :email AND password = :password")
    fun getUser(email: String, password : String): LiveData<User>

    @Query("SELECT COUNT() FROM user_table WHERE email = :email AND password = :password")
    suspend fun checkUser(email: String, password : String): Int
}