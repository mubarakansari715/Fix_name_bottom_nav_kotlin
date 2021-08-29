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
import com.example.kotlin_28_7_2021.PersonDetailsInterface
import com.example.kotlin_28_7_2021.R
import com.example.kotlin_28_7_2021.RecycelViewApiInterfacea
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_person.*
import kotlinx.android.synthetic.main.fragment_volley.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PersonFragment : Fragment() {
    val url: String = "https://jsonplaceholder.typicode.com/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_person, container, false)
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
        val retrofitBulder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
            .create(PersonDetailsInterface::class.java)
        val retrofitData = retrofitBulder.getPersonData()
        retrofitData.enqueue(object : Callback<List<PersonModelClass>?> {
            override fun onResponse(
                call: Call<List<PersonModelClass>?>,
                response: Response<List<PersonModelClass>?>
            ) {
                val responseBody = response.body()!!

                val listData = ArrayList<PersonModelClass>()
                for (m in responseBody) {
                    listData.add(PersonModelClass(m.id, m.name, m.username, m.phone, m.email))
                }
                val mAdapters = PersonAdapter(requireActivity(), listData)
                person_recycleView.layoutManager = LinearLayoutManager(requireActivity())
                person_recycleView.adapter = mAdapters

            }

            override fun onFailure(call: Call<List<PersonModelClass>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        ////////////


    }
}