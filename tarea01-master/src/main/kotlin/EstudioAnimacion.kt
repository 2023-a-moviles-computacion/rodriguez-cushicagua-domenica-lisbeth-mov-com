class EstudioAnimacion {
    var idEstudio: Int?  =0;
    var nombreEstudio: String =" ";
    var direccion: String = "";
    var numeroAnimadores: Int? =0;
    var presupuesto: Double  =0.0

    constructor(
        idEstudio:Int?,
        nombreEstudio: String,
        direccion:String,
        numeroAnimadores: Int?,
        presupuesto:Double)
    {
        this.idEstudio = idEstudio
        this.nombreEstudio =nombreEstudio
        this.direccion = direccion
        this.numeroAnimadores =  numeroAnimadores
        this.presupuesto = presupuesto
    }

}