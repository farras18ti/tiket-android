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
import com.example.andoirdtiket.util.Constant
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener {
            val jsonobj = JSONObject()
            val queue = Volley.newRequestQueue(this)
            val url = "${Constant.BASE_URL}/user/register"

            jsonobj.put("name", txName.text.toString())
            jsonobj.put("email", txEmail.text.toString())
            jsonobj.put("password", txPasswordReg.text.toString())

            val jsonRequest = JsonObjectRequest(
                Request.Method.POST, url, jsonobj,
                Response.Listener {
                    Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                },
                Response.ErrorListener {
                    Toast.makeText(this, "Registrasi Gagal", Toast.LENGTH_SHORT).show()
                })

            queue.add(jsonRequest)
        }
    }
}