import java.util.Date

fun main(args: Array<String>) {
    println("Hello World!")

    //Tipos de variables

    //INMUTABLES (NO SE REASIGNAN "=")
    val inmutable: String = "Adrian";
    //inmutable="Vicente";

    //Mutables(Se Reasignan)
    var mutable: String = "Vicente";
    mutable = "Adrian";

    // val > var es lo que utilizaremos en el codígo

    //Duck Typing
    val ejemploVariable = "Domenica Rodriguez"
    val edadEjemplo: Int = 12
    //el metodo .trim() quita los espacios de la variabe
    ejemploVariable.trim()
    //ejemploVariable = edadEjemplo;

    //Variable primitiva
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true

    //Clases Java
    val fechaNacimiento: Date = Date()

    //SWITCH que en Kotlin es post when
    val estadoCivilWhen = "C"
    when (estadoCivilWhen) {
        ("C") -> {
            println("Casado")
        }
        ("S") -> {
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }

    val coqueteo = if (estadoCivilWhen == "S") "Si" else "No"

    //colocar la funcion
    calcularSueldo(10.00)
    calcularSueldo(10.00, 12.00, 20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00) // Named Parameters
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) //  Parametros nombrados

    val sumaUno = Suma(1, 1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null, null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    //AREGLOS
    // Tipos de Arreglos

    // Arreglo Estatico
    val arregloEstatico: Array<Int> = arrayOf<Int>(1, 2, 3)
    println(arregloEstatico)

    // Arreglo Dinámicos son los que pueden modificar el contenido
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)


    //OPERADORES -> Sirve para los arreglos estativos y dinamicos
    //FOR EACH -> Unit
    //Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach { valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)


    //MAP ->Muta el arreglo(Cambia el arreglo)
    //cambia y modifica el arreglo pero en otro arreglo

    //1.- Envia el nuevo valos de la interacion
    //2.- Nos devuelve un NUEVO ARREGLO CON LOS VALORES MODICADOS

     val respuestaMap: List<Double> = arregloDinamico
         .map { valorActual: Int ->
             return@map valorActual . toDouble () + 100.00
         }

    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map {it + 15}


    //Filter -> FILTRA EL ARREGLO
    //1.- Devolver una expresion (TRUE O FALSE)
    //2.- Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5// Es La Expresion Condicion
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter {it<=5}
    println(respuestaFilter)
    println(respuestaFilterDos)

    //OR AND
    //OR -> ANY (Alguno cumple?)
    //OR -> ALL (Todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int->
            return@any (valorActual >5)
        }
    println(respuestaAny)//true

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int->
            return@ll (valorActual >5)
        }
    println(respuestaAll)//true

    //REDUCE -> Valor acumulado
    //Valor acumualdo = 0 (Siempre 0 en lenguaje Kotlin)
    //[1, 2, 3, 4, 5] -> Sumeme todos los numeros del arreglo
    //valorIteracion1 = valorEmpieza +1 = 0 + 1 = 1 -> Iteracion 1
    //valorIteracion2 = valorIteracion1 +2 = 1 + 2= 1 -> Iteracion 2
    //valorIteracion3 = valorIteracion2 +3 = 3 + 3= 6 -> Iteracion 3
    //valorIteracion4 = valorIteracion3 +4 = 6 + 4= 10 -> Iteracion 4
    //valorIteracion5 = valorIteracion4 +5 = 10 + 5= 15 -> Iteracion 5

    val respuestaReduce : Int = arregloDinamico
        .reduce {
                acumulado: Int, valorActual: Int ->
            return@reduce(acumulado + valorActual)// -> Logica negocio
        }
    println (respuestaReduce)//78

}

//Escrinir funciones en donde void -> Unit
fun imprimirNombre(nombre : String): Unit{
    println("Nombre : ${nombre}")
}

fun calcularSueldo(
    sueldo: Double,//Requerido
    tasa: Double = 12.00, // Opcional (defecto)
    bonoEspecial: Double? = null, //Opcional null -> nullable
): Double{
    //cualquier tipo de dato sea nullable
    //Int -> Int? (nullable)
    //String -> String? (nullable)
    //Date -> Date? (nullable)
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) + bonoEspecial
    }
}


abstract class  NumerosJava{
    //1 modificador de acceso
    //2 si es mutable o inmutable
    //nombre de la variable
    //tipo de variable
    protected val numeroUno : Int
    private  val numeroDos: Int

    constructor(
        uno:Int,
        dos: Int
    ){//Bloque de codigo del constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}


abstract class Numeros( // Constructor PRIMARIO
    // Ejemplo:
    // uno: Int, (Parametro (sin modificador de acceso))
    // private var uno: Int, // Propiedad Publica Clase numeros.uno
    // var uno: Int, // Propiedad de la clase (por defecto es PUBLIC)
    // public var uno: Int,
    protected val numeroUno: Int, // Propiedad de la clase protected numeros.numeroUno
    protected val numeroDos: Int, // Propiedad de la clase protected numeros.numeroDos
){
    // var cedula: string = "" (public es por defecto)
    // private valorCalculado: Int = 0 (private)
    init {
        this.numeroUno; this.numeroDos; // this es opcional
        numeroUno; numeroDos; // sin el "this", es lo mismo
        println("Inicializando")
    }
}

//clase

class Suma( // Constructor Primario Suma
uno: Int, //Parametro
dos: Int //Parametro
): Numeros(uno, dos ){//<- Constructor del Padre
    init{ // Bloque constructor primario
        this.numeroUno; numeroUno;
        this.numeroDos; numeroDos;
    }
}

constructor(// segundo constructor
uno:Int?, //parametros
dos: Int //parametros
): this( //llamada constructor primario
    if(uno==null) 0 else uno,
    dos
){//si necesitamos bloque de codigo lo usamos
    numeroUno;
}

constructor(// tercer constructor
uno: Int, //parametros
dos : Int? //parametros
) :this(//llamada cosntructor primario
uno,
if(dos==null) 0  else uno
)//si no lo necesitamos al bloque de codigo "{}" lo omitimos


constructor(//cuarto constructor
uno: Int?,//parametros
dos: Int? //parametros
) : this(// llamada constructor primario
if(uno == null) 0  else uno,
if(dos == null) 0  else dos

)

public fun sumar(): Int{ // public por defecto, usar private o protected
    val total = numeroUno + numeroDos
    agregarHistorial(total)
    return total
}
//parecido al static sin instanciar ningun objeto
companion objedt{ //Atributos y metodos "Compartidos"
    // entren las instancias
    val pi = 3.14
    fun elevarAlCuadrado(num: Int){
        return num * num
    }
    val historialSumas = arrayListof<Int>()
    fun agregarHistorial (valorNuevaSuma: Int){
        historialSumas.add(valorNuevaSuma)
        }
    }

}










