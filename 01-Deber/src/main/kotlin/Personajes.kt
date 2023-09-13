class Personajes(
    var idPersonaje: Int? = 0,
    var nombrePersonaje: String = "",
    var edadPersonaje: Int? = 0,
    var estatura: Double = 0.0,
    var peso: Double = 0.0
) {
    override fun toString(): String {
        return "$idPersonaje,$nombrePersonaje,$edadPersonaje,$estatura,$peso"
    }
}
