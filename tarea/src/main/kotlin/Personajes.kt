class Personajes {

    var idPersonaje: Int? = 0;
    var nombrePersonaje: String = "";
    var edadPersonaje: Int? = 0;
    var estarura: Double = 0.0;
    var peso: Double = 0.0;

    constructor(
        idPersonaje: Int?,
        nombrePersonaje: String,
        edadPersonaje: Int?,
        estatura: Double,
        peso: Double,

        ) {
        this.idPersonaje = idPersonaje
        this.nombrePersonaje = nombrePersonaje
        this.edadPersonaje = edadPersonaje
        this.estarura = estatura
        this.peso=peso

    }

}