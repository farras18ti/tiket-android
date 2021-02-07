package com.example.andoirdtiket.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.andoirdtiket.R
import com.example.andoirdtiket.adapter.FilmAdapter
import com.example.andoirdtiket.models.Film
import com.example.andoirdtiket.util.Constant
import kotlinx.android.synthetic.main.activity_film.*

class ListFilmActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LOKASI = "extra_lokasi"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)

        setupRecyvlerView()
    }

    private fun setupRecyvlerView() {
        val lokasi = intent.extras?.getSerializable(EXTRA_LOKASI)

        val queue = Volley.newRequestQueue(this)
        val url = "${Constant.BASE_URL}/film/${lokasi}"


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val filmResponse = response.getJSONArray("data")
                val filmList = ArrayList<Film>()

                for(i in 0 until filmResponse.length()) {
                    val item = filmResponse.getJSONObject(i)

                    val film = Film(
                        nama_film = item.getString("nama_film"),
                        harga = item.getString("harga"),
                        lokasi = item.getString("lokasi")
                    )
                    filmList.add(film)
                }

                val adapter = FilmAdapter(filmList)
                adapter.setOnClickListener {
                    val intent = Intent(this, PenjualanActivity::class.java)
                    intent.putExtra(PenjualanActivity.EXTRA_FILM, it)
                    startActivity(intent)
                }

                rvFilm.apply {
                    this.adapter = adapter
                    layoutManager = LinearLayoutManager(this@ListFilmActivity)
                }

            },
            {
                Toast.makeText(this, "${it.toString()}", Toast.LENGTH_SHORT).show()
            }
        )

        queue.add(jsonObjectRequest)
    }
}