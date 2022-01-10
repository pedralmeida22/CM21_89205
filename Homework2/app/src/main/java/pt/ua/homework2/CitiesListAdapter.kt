package pt.ua.homework2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CitiesListAdapter(private val list: List<String>): RecyclerView.Adapter<CitiesListAdapter.CitiesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {

        return CitiesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        val current = list[position]
        holder.bind(current)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class CitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityItemView: TextView = itemView.findViewById(R.id.txv_destination)

        fun bind(city: String?){
            cityItemView.text = city
        }

        companion object {
            fun create(parent: ViewGroup): CitiesViewHolder {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
                return CitiesViewHolder(view)
            }
        }
    }
}