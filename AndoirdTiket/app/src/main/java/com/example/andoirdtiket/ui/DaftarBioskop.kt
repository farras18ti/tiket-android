package com.example.andoirdtiket.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.andoirdtiket.R

class DaftarBioskop : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_bioskop)
        val cinemaxxi = findViewById<View>(R.id.cinemaXXI) as Button
        cinemaxxi.setOnClickListener {
            val i = Intent(this@DaftarBioskop, ListFilmActivity::class.java)
            i.putExtra(ListFilmActivity.EXTRA_LOKASI, "cinema")
            startActivity(i)
        }

        val cgv = findViewById<View>(R.id.Cgv) as Button
        cgv.setOnClickListener {
            val i = Intent(this@DaftarBioskop, ListFilmActivity::class.java)
            i.putExtra(ListFilmActivity.EXTRA_LOKASI, "cgv")
            startActivity(i)
        }

        val cinepolis = findViewById<View>(R.id.Cinepolis) as Button
        cinepolis.setOnClickListener {
            val i = Intent(this@DaftarBioskop, ListFilmActivity::class.java)
            i.putExtra(ListFilmActivity.EXTRA_LOKASI, "cinepolis")
            startActivity(i)
        }

        val box = findViewById<View>(R.id.Box) as Button
        box.setOnClickListener {
            val i = Intent(this@DaftarBioskop, ListFilmActivity::class.java)
            i.putExtra(ListFilmActivity.EXTRA_LOKASI, "box")
            startActivity(i)
        }
    }
}