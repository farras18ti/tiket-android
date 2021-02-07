package com.example.andoirdtiket.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.andoirdtiket.R
import com.example.andoirdtiket.models.Film
import kotlinx.android.synthetic.main.item_film.view.*

class FilmAdapter(
    var films: List<Film>
) : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    inner class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        var item = films?.get(position)
        holder.itemView.apply {
            btnFilm.text = films[position].nama_film
            btnFilm.setOnClickListener {
                onItemClickListener?.let {
                    it(item)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return films.size
    }

    private var onItemClickListener: ((Film) -> Unit)? = null

    fun setOnClickListener(listener: (Film) -> Unit) {
        onItemClickListener = listener
    }
}