package com.example.kotlin_28_7_2021

import com.example.kotlin_28_7_2021.ModelClass.RecycelViewModelClass
import retrofit2.Call
import retrofit2.http.GET

interface RecycelViewApiInterfacea {
//https://jsonplaceholder.typicode.com/photos
    @GET("photos")
    fun getData(): Call<List<RecycelViewModelClass>>
}