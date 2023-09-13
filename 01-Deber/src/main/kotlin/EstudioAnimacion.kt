class EstudioAnimacion(
    var idEstudio: Int? = 0,
    var nombreEstudio: String = "",
    var direccion: String = "",
    var numeroAnimadores: Int? = 0,
    var presupuesto: Double = 0.0
) {
    override fun toString(): String {
        return "$idEstudio,$nombreEstudio,$direccion,$numeroAnimadores,$presupuesto"
    }
}
