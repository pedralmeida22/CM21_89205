package pt.ua.homework2

import androidx.lifecycle.ViewModel

class CitiesViewModel : ViewModel() {       // responsable for data

    fun generateDummyList(size: Int) : List<String> {
        val list = ArrayList<String>()

        for (i in 0 until size){
            list.add(i.toString())
        }

        return list
    }

}