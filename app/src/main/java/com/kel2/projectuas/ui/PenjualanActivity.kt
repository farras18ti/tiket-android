package com.kel2.projectuas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.kel2.projectuas.R
import com.kel2.projectuas.models.Film
import com.kel2.projectuas.models.Tiket
import com.kel2.projectuas.models.User
import com.kel2.projectuas.util.Constant
import com.kel2.projectuas.util.Session
import kotlinx.android.synthetic.main.activity_penjualan.*
import org.json.JSONObject

class PenjualanActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_FILM = "extra_film"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penjualan)

        bindNameUser()

        val film = intent.extras?.getParcelable<Film>(EXTRA_FILM)

        val harga = film?.harga
        val kursi = etKursi.text
        var bayar = etBayar.text

        tvFilm.text = film?.nama_film
        tvHarga.text = harga


        btnProses.setOnClickListener {
            val total = harga?.toInt()?.times(kursi.toString().toInt())
            if((total ?: 0) > bayar.toString().toInt()) {
                Toast.makeText(this, "Uang anda tidak cukup", Toast.LENGTH_LONG).show()
            } else {
                val kembali = bayar.toString().toInt() - (total ?: 0)

                tvTotal.text = total.toString()
                tvKembali.text = kembali.toString()
                tvKeterangan.text = "Pembelian Berhasil"

                val tikets = Tiket(
                    jumlah_kursi = kursi.toString(),
                    total = total.toString(),
                    id_user = Session.id,
                    id_film = film?.id
                )

                requestAddTiket(tikets)
            }
        }

        btnBack.setOnClickListener {
            startActivity(Intent(this, DaftarBioskop::class.java))
        }
    }

    private fun requestAddTiket(tiket: Tiket) {
        val jsonobj = JSONObject()

        jsonobj.put("jumlah_kursi", tiket.jumlah_kursi)
        jsonobj.put("lokasi", tiket.lokasi)
        jsonobj.put("total", tiket.total)
        jsonobj.put("id_user", Session.id)

        val queue = Volley.newRequestQueue(this)
        val url = "${Constant.BASE_URL}/tiket/create"

        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonobj,
            Response.Listener { response ->
                Toast.makeText(this, "Pembelian Tiket Berhasil", Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener { response ->
                Toast.makeText(this, "${response.toString()}", Toast.LENGTH_SHORT).show()
            })

        queue.add(jsonRequest)
    }

    private fun requestAddFilm(film: Film?) {
        val jsonobj = JSONObject()

        jsonobj.put("nama_film", film?.nama_film)
        jsonobj.put("harga", film?.harga)

        val queue = Volley.newRequestQueue(this)
        val url = "${Constant.BASE_URL}/film/create"

        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonobj,
            Response.Listener { response ->
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener { response ->
                Toast.makeText(this, "${response.toString()}", Toast.LENGTH_SHORT).show()
            })

        queue.add(jsonRequest)
    }

    private fun bindNameUser() {
        val queue = Volley.newRequestQueue(this)
        val url = "${Constant.BASE_URL}/user/${Session.id}"

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                val data = response.getJSONObject("data")
                val user = User(
                    name = data.getString("name")
                )
                tvName.text = user?.name
            },
            Response.ErrorListener { response ->
                Toast.makeText(this, "${response.toString()}", Toast.LENGTH_SHORT).show()
            })

        queue.add(jsonRequest)
    }
}
