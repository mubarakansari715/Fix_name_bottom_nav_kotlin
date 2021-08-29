package com.example.kotlin_28_7_2021.Fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.kotlin_28_7_2021.Adapter.RecycelViewAdapters
import com.example.kotlin_28_7_2021.ModelClass.RecycelViewModelClass
import com.example.kotlin_28_7_2021.R
import com.example.kotlin_28_7_2021.RecycelViewApiInterfacea
import kotlinx.android.synthetic.main.fragment_person.*
import kotlinx.android.synthetic.main.fragment_volley.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VolleyFragment : Fragment() {
    private val StringUrl = "https://jsonplaceholder.typicode.com/"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_volley, container, false)
        getData(view)
        return view
    }

    private fun getData(view: View?) {
        val prog = ProgressDialog(requireActivity())
        prog.setTitle("Checking Network")
        prog.setMessage("Please wait few minutes")
        //prog.setFeatureDrawable(R.drawable.ic_launcher_background)
        prog.setCancelable(true)
        Handler().postDelayed({ prog.dismiss() }, 2000)
        prog.show()
        val queue = Volley.newRequestQueue(context)
        val url = "https://jsonplaceholder.typicode.com/photos"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->

                textView.text = "Response is: ${response.substring(0, 500)}"
            },
            { textView.text = "That didn't work!" })
        queue.add(stringRequest)

        //////////
        val retrofitBuilder1 = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(StringUrl)
            .build()
            .create(RecycelViewApiInterfacea::class.java)

        val retrofitData1 = retrofitBuilder1.getData()
        retrofitData1.enqueue(object : Callback<List<RecycelViewModelClass>?> {
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
                recycal_view1.layoutManager = LinearLayoutManager(requireContext())
                recycal_view1.adapter = myAdapter

            }

            override fun onFailure(call: Call<List<RecycelViewModelClass>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

}