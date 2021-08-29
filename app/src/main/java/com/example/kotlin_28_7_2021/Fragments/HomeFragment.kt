package com.example.kotlin_28_7_2021.Fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_28_7_2021.Adapter.PersonAdapter
import com.example.kotlin_28_7_2021.Adapter.RecycelViewAdapters
import com.example.kotlin_28_7_2021.ModelClass.PersonModelClass
import com.example.kotlin_28_7_2021.ModelClass.RecycelViewModelClass
import com.example.kotlin_28_7_2021.R
import com.example.kotlin_28_7_2021.RecycelViewApiInterfacea
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {
    private val StringUrl = "https://jsonplaceholder.typicode.com/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        getData(view)

        return view
    }

    private fun getData(view: View?) {
        val prog = ProgressDialog(requireActivity())
        prog.setTitle("Checking Network")
        prog.setMessage("Please wait few minutes")
        //prog.setFeatureDrawable(R.drawable.ic_launcher_background)
        prog.setCancelable(false)
        Handler().postDelayed({ prog.dismiss() }, 2000)
        prog.show()
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(StringUrl)
            .build()
            .create(RecycelViewApiInterfacea::class.java)

        val retrofitData = retrofitBuilder.getData()// calling from apiinterface file getData()

        //blow code genrate ctrl+shift+space
        retrofitData.enqueue(object : Callback<List<RecycelViewModelClass>?> {
            override fun onResponse(
                call: Call<List<RecycelViewModelClass>?>,
                response: Response<List<RecycelViewModelClass>?>
            ) {
                val responseBody = response.body()!!

                val listData = ArrayList<RecycelViewModelClass>()

                for (m in responseBody) {
                    listData.add(RecycelViewModelClass(m.id, m.title, m.url))
                }
                val myAdapter = RecycelViewAdapters(requireActivity(), listData)
                recycal_view.layoutManager = LinearLayoutManager(requireContext())
                recycal_view.adapter = myAdapter

            }

            override fun onFailure(call: Call<List<RecycelViewModelClass>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })


    }

}