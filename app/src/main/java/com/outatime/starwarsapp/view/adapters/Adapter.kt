package com.outatime.starwarsapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.outatime.starwarsapp.R
import com.outatime.starwarsapp.databinding.CharacterElementBinding
import com.outatime.starwarsapp.model.People
import com.outatime.starwarsapp.util.Constants
import com.outatime.starwarsapp.view.activities.MainActivity

class Adapter(private val context: Context, private val people: People): RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(view: CharacterElementBinding): RecyclerView.ViewHolder(view.root) {
        val tvPosition = view.tvPosition
        val tvName = view.tvName
        val tvHeight = view.tvHeight
        val tvBirthday = view.tvBirthday
        val tvGender = view.tvGender
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterElementBinding.inflate(LayoutInflater.from(context))

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        for ((clave, valor) in Constants.CHARACTERS){
            if (clave.equals(people.results[position].name)) {
                holder.tvPosition.text = valor.toString()
            }
        }

        holder.tvName.text = people.results[position].name
        holder.tvHeight.text = people.results[position].height
        holder.tvBirthday.text = people.results[position].birth_year
        holder.tvGender.text = people.results[position].gender

        holder.itemView.setOnClickListener {
            if(context is MainActivity) {
                print("Name: ${people.results[position].name}\n\n")
                context.selectedCharacter(people, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return people.results.size
    }
}