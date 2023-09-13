import java.io.File

fun main(args: Array<String>) {
    val estudiosAnimacion = ArrayList<EstudioAnimacion>()
    val personajes = ArrayList<Personajes>()
    val fileManager = FileManager()
    val pathEstudioAnimacion = "C://Users//escritorio.virtual18//Documents//GitHub//DomenicaGitHub//rodriguez-cushicagua-domenica-lisbeth-mov-com//tarea01//tarea01//src//main//kotlin//EstudioAnimacion"
    val pathPersonajes= "C://Users//escritorio.virtual18//Documents//GitHub//DomenicaGitHub//rodriguez-cushicagua-domenica-lisbeth-mov-com//tarea01//tarea01//src//main//kotlin//Personajes"

    while (true) {
        println(
            "Seleciona una opcion\n" +
                    "1: Estudio de Animacion\n" +
                    "2: Personajes\n" +
                    "3: Salir"
        )

        print("Ingresa la opción: ")
        val option = readLine()?.uppercase()

        when (option) {
            "1" -> {
                var flagEstudioAnimacion = true

                while (flagEstudioAnimacion) {
                    println(
                        "Seleciona una opcion:\n" +
                                "1: Agrega un Estudio de Animacion\n" +
                                "2: Listar de los Estudio de Animacion \n" +
                                "3: Actualiza El Estudio de Animacion \n"+
                                "4: Elimina El Estudio de Animacion \n" +
                                "5: Regresar\n"+
                                "6: Salir"
                    )
                    print("Ingresa la opción: ")
                    var optionEstudioAnimacion = readLine()?.uppercase()

                    when (optionEstudioAnimacion) {
                        "1" -> {
                            val estudioAnimacion = ingresarEstudio()
                            estudiosAnimacion.add(estudioAnimacion)
                            println("\nEstudio de Animacion agregado exitosamente.\n")

                            val content = estudiosAnimacion.joinToString("\n") { it.toString() }
                            fileManager.saveTextFile(fileName = "$pathEstudioAnimacion.txt", content = content)
                            println("\nLa lista de Estudios se ha guardado\n")
                        }

                        "2" -> {
                            println("\nListado de Estudios de Animacion:")
                            val file = File("$pathEstudioAnimacion.txt")

                            if (file.exists()) {
                                file.forEachLine { line ->
                                    val estudio = line.split(",")
                                    println("ID del Estudio: ${estudio[0]}")
                                    println("Nombre del Estudio: ${estudio[1]}")
                                    println("Direccion: ${estudio[2]}")
                                    println("Numero de Animadores: ${estudio[3]}")
                                    println("Presupuesto: ${estudio[4]}\n")
                                }
                            } else {
                                println("\nEl archivo del Estudios de Animacion no existe.\n")
                            }
                        }

                        "3" -> {
                            print("\nIngresa el nombre del Estudio de Animacion a actualizar: ")
                            val actualizarEstudio = readLine().toString()
                            val file = File("$pathEstudioAnimacion.txt")
                            val tempFile = File.createTempFile("temp", null)

                            var estudioActualizado = false

                            if (file.exists()) {
                                file.forEachLine { line ->
                                    val estudio = line.split(",")
                                    if (estudio[1].equals(actualizarEstudio, ignoreCase = true)) {
                                        println("\nEstudio de Animacion encontrado. Ingresa los nuevos datos:")
                                        val estudioAnimacionActualizado = ingresarEstudio()
                                        val nuevaLinea = "${estudioAnimacionActualizado.idEstudio},${estudioAnimacionActualizado.nombreEstudio},${estudioAnimacionActualizado.direccion},${estudioAnimacionActualizado.numeroAnimadores},${estudioAnimacionActualizado.presupuesto}"
                                        tempFile.appendText(nuevaLinea + "\n")
                                        println("\nEstudio de Animacion actualizado .\n")
                                        estudioActualizado = true
                                    } else {
                                        // El estudio de animación no coincide, copiamos la línea original al archivo temporal
                                        tempFile.appendText(line + "\n")
                                    }
                                }
                                // Reemplazamos el archivo original con el archivo temporal
                                tempFile.copyTo(file, true)
                                tempFile.delete() // Eliminamos el archivo temporal
                            } else {
                                println("\nEl archivo de Estudios de Animacion no existe.\n")
                            }

                            if (!estudioActualizado) {
                                println("\nNo se encontró ningún Estudio de Animacion con ese nombre.\n")
                            }
                        }


                        "4" -> {
                            print("\nIngresa el nombre del Estudio de Animacion a eliminar: ")
                            val eliminarEstudio = readLine().toString()
                            val file = File("$pathEstudioAnimacion.txt")
                            val tempFile = File.createTempFile("temp", null)

                            if (file.exists()) {
                                file.forEachLine { line ->
                                    val estudio = line.split(",")
                                    if (!estudio[1].equals(eliminarEstudio, ignoreCase = true)) {
                                        // El estudio no coincide, lo copiamos al archivo temporal
                                        tempFile.appendText(line + "\n")
                                    }
                                }
                                // Reemplazamos el archivo original con el archivo temporal
                                tempFile.copyTo(file, true)
                                tempFile.delete() // Eliminamos el archivo temporal
                                println("\nEstudio de Animacion eliminado del archivo.\n")
                            } else {
                                println("\nEl archivo de Estudios de Animacion no existe.\n")
                            }
                        }

                        "5" -> {
                            flagEstudioAnimacion = false
                        }
                        "6" -> {
                            flagEstudioAnimacion = false
                            return
                        }
                        else -> {
                            println("Opción inválida")
                        }
                    }
                }
            }

            "2" -> {
                var flagPlanetas = true

                while (flagPlanetas) {
                    println(
                        "Seleciona una opcion:\n" +
                                "1: Agrega un Personaje\n" +
                                "2: Listar los Personajes guardados\n" +
                                "3: Actualiza el Personaje\n" +
                                "4: Eliminar el Personaje\n" +
                                "5: Regresar\n" +
                                "6: Salir"
                    )
                    print("Ingresa la opción: ")
                    var optionPersonajes = readLine()?.uppercase()

                    when (optionPersonajes) {
                        "1" -> {
                            val personaje = ingresarPersonaje()
                            personajes.add(personaje)
                            println("\nPersonaje agregado.\n")

                            val content = personajes.joinToString("\n") { it.toString() }
                            fileManager.saveTextFile(fileName = "$pathPersonajes.txt", content = content)
                            println("\nLa lista de personajes se ha guardado\n")

                        }
                        "2" -> {
                            println("\nListado de Personajes:")
                            val file = File("$pathPersonajes.txt")

                            if (file.exists()) {
                                file.forEachLine { line ->
                                    val personaje = line.split(",")
                                    println("ID del Personaje: ${personaje[0]}")
                                    println("Nombre del Personaje: ${personaje[1]}")
                                    println("Edad del Personaje: ${personaje[2]}")
                                    println("Estatura: ${personaje[3]}")
                                    println("Peso: ${personaje[4]}\n")
                                }
                            } else {
                                println("\nEl Personajes no existe.\n")
                            }
                        }

                        "3" -> {
                            print("\nIngresa el nombre del Personaje a actualizar: ")
                            val actualizarPersonaje = readLine().toString()
                            val file = File("$pathPersonajes.txt")
                            val tempFile = File.createTempFile("temp", null)

                            var personajeActualizadoflag = false

                            if (file.exists()) {
                                file.forEachLine { line ->
                                    val personaje = line.split(",")
                                    if (personaje[1].equals(actualizarPersonaje, ignoreCase = true)) {
                                        println("\nPersonaje encontrado. Ingresa los nuevos datos:")
                                        val personajeActualizado = ingresarPersonaje()
                                        val nuevaLinea = "${personajeActualizado.idPersonaje},${personajeActualizado.nombrePersonaje},${personajeActualizado.edadPersonaje},${personajeActualizado.estatura},${personajeActualizado.peso}"
                                        tempFile.appendText(nuevaLinea + "\n")
                                        println("\nPersonaje actualizado en el archivo.\n")
                                        personajeActualizadoflag = true
                                    } else {
                                        // El personaje no coincide, copiamos la línea original al archivo temporal
                                        tempFile.appendText(line + "\n")
                                    }
                                }
                                // Reemplazamos el archivo original con el archivo temporal
                                tempFile.copyTo(file, true)
                                tempFile.delete() // Eliminamos el archivo temporal
                            } else {
                                println("\nEl archivo de Personajes no existe.\n")
                            }

                            if (!personajeActualizadoflag) {
                                println("\nNo se encontró ningún Personaje con ese nombre.\n")
                            }
                        }


                        "4" -> {
                            print("\nIngresa el nombre del Personaje a eliminar: ")
                            val eliminarPersonaje = readLine().toString()
                            val file = File("$pathPersonajes.txt")
                            val tempFile = File.createTempFile("temp", null)

                            if (file.exists()) {
                                file.forEachLine { line ->
                                    val personaje = line.split(",")
                                    if (!personaje[1].equals(eliminarPersonaje, ignoreCase = true)) {
                                        // El personaje no coincide, lo copiamos al archivo temporal
                                        tempFile.appendText(line + "\n")
                                    }
                                }
                                // Reemplazamos el archivo original con el archivo temporal
                                tempFile.copyTo(file, true)
                                tempFile.delete() // Eliminamos el archivo temporal
                                println("\nPersonaje eliminado del archivo.\n")
                            } else {
                                println("\nEl archivo de Personajes no existe.\n")
                            }
                        }


                        "5" -> {
                            flagPlanetas = false
                        }
                        "6" -> {
                            flagPlanetas = false
                            return
                        }
                        else -> {
                            println("Opción inválida seleccionada.")
                        }
                    }
                }
            }

            "3" -> {
                println("Fin del Programa")
                return
            }

            else -> {
                println("Opción inválida")
            }
        }
    }
}

fun ingresarEstudio(): EstudioAnimacion {
    println("\n--- Nuevo Estudio de Animacion ---\n")
    print("Ingresa un ID del Estudio: ")
    val idEstudio = readLine()?.toInt()?: 0
    print("Ingresa el Nombre del Estudio: ")
    val nombreEstudio = readLine().toString()
    print("Ingresa la Direccion del Estudio: ")
    val direccion = readLine().toString()
    print("Ingresa el Numero de Animadores del Estudio: ")
    val numeroAnimadores = readLine()?.toInt() ?: 0
    print("Ingresa el Presupuesto total del Estudio: ")
    val presupuesto = readLine()?.toDouble() ?: 0.0

    return EstudioAnimacion(idEstudio, nombreEstudio, direccion, numeroAnimadores, presupuesto)
}


fun ingresarPersonaje(): Personajes {
    println("\n--- Nuevo Personaje Animado---\n")
    print("Ingresa un ID del Personaja: ")
    val idPersonaje = readLine()?.toInt()
    print("Ingresa el Nombre del Personaje: ")
    val nombrePersonaje = readLine().toString()
    print("Ingresa la Edad del Personaje: ")
    val edadPersonaje = readLine()?.toInt()
    print("Ingresa la Estatura del Personaje: ")
    val estatura = readLine()?.toDouble()
    print("Ingresa el Peso del Personaje: ")
    val peso = readLine()?.toDouble()
    return Personajes(idPersonaje, nombrePersonaje, edadPersonaje ?: 0, estatura ?: 0.0, peso ?: 0.0)
}

class FileManager {
    fun saveTextFile(fileName: String, content: String) {
        val file = File(fileName)
        file.writeText(content)
    }

    fun readTextFile(fileName: String): String {
        val file = File(fileName)
        return file.readText()
    }
}