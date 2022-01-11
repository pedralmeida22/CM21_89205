package pt.ua.homework2

import androidx.lifecycle.ViewModel

class CitiesViewModel : ViewModel() {       // responsable for data

    val mapCities = mapOf("Aveiro" to "1010500", "Porto" to "1131200", "Lisboa" to "1110600")

    fun citiesList(): Set<String> {
        return mapCities.keys
    }

    fun generateDummyList(size: Int) : List<String> {
        val list = ArrayList<String>()

        for (i in 0 until size){
            list.add(i.toString())
        }

        return list
    }

}