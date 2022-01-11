package pt.ua.homework2.datamodel

class CallResponse {

    data class City(
        val owner: String,
        val country: String,
        val data: List<Weather>
    )

    data class Weather(
        val precipitaProb: Double,
        val tMin: Double,
        val tMax: Double,
        val predWindDir: String,
        val idWeatherType: Int,
        val classWindSpeed: Int,
        val longitude: String,
        val forecastDate: String,
        val latitude: String
    )
}