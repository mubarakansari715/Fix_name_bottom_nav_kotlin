package com.example.kotlin_28_7_2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_28_7_2021.Fragments.HomeFragment
import com.example.kotlin_28_7_2021.Fragments.PersonFragment
import com.example.kotlin_28_7_2021.Fragments.VolleyFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottome_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frame_layout, HomeFragment(), getString(R.string.fg_home_tag))
                        commit()
                    }
                }
                R.id.nav_Volley -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(
                            R.id.frame_layout,
                            VolleyFragment(),
                            getString(R.string.fg_volley_tag)
                        )
                        commit()
                    }
                }
                R.id.nav_person -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(
                            R.id.frame_layout,
                            PersonFragment(),
                            getString(R.string.fg_Person_tag)
                        )
                        commit()
                    }
                }

            }
            true
        }
        bottome_navigation.selectedItemId = R.id.nav_home
    }
}