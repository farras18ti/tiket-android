package com.example.andoirdtiket.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.andoirdtiket.R
import com.example.andoirdtiket.models.User
import com.example.andoirdtiket.util.Constant
import com.example.andoirdtiket.util.Session
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Session.init(applicationContext)
        btnLogin.setOnClickListener {
            val jsonobj = JSONObject()
            jsonobj.put("email", txEmail.text.toString())
            jsonobj.put("password", txPassword.text.toString())

            val queue = Volley.newRequestQueue(this)
            val url = "${Constant.BASE_URL}/user/login"

            val jsonRequest = JsonObjectRequest(
                Request.Method.POST, url, jsonobj,
                Response.Listener { response ->
                    val data = response.getJSONObject("data")
                    val user = User(
                        id = data.getString("id")
                    )
                    Session.id = user.id
                    Toast.makeText(this, "Anda Berhasil Login ${data.getString("name")}", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, DaftarBioskop::class.java))
                },
                Response.ErrorListener { response ->
                    Toast.makeText(this, "${response.toString()}", Toast.LENGTH_SHORT).show()
                })

            queue.add(jsonRequest)
        }

        tvCreateAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}