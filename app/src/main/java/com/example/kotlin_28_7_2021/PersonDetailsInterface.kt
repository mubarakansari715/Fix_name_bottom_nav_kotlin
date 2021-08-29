package com.example.kotlin_28_7_2021

import com.example.kotlin_28_7_2021.ModelClass.PersonModelClass
import retrofit2.Call
import retrofit2.http.GET

interface PersonDetailsInterface {
    @GET("users")
    fun getPersonData(): Call<List<PersonModelClass>>
}