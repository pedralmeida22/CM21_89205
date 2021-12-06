package pt.ua.dialer

class Contact(
    val name: String,
    var phone_number: String
) {
    override fun toString(): String {
        return "$name, $phone_number"
    }
}