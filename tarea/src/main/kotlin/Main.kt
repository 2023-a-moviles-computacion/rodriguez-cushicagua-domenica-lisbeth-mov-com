import java.io.File

fun main(args: Array<String>) {
    val estudiosAnimacion = ArrayList<EstudioAnimacion>()
    val personajes = ArrayList<Personajes>()
    val fileManager = FileManager()
    val pathEstudioAnimacion = "C://Users//escritorio.virtual18//Documents//GitHub//DomenicaGitHub//rodriguez-cushicagua-domenica-lisbeth-mov-com//tarea01//Estudio//src//main//kotlin//EstudioAnimacion"
    val pathPersonajes= "C://Users//escritorio.virtual18//Documents//GitHub//DomenicaGitHub//rodriguez-cushicagua-domenica-lisbeth-mov-com//tarea01//Estudio//src//main//kotlin//Personajes"

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
                                "2: Listar de los El Estudio de Animacion guardados\n" +
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
                            fileManager.saveTextFile(fileName = pathEstudioAnimacion+"$estudioAnimacion.txt", content = content)
                            println("\nLa lista de Estudios se ha guardado\n")
                        }
                        "2" -> {
                            println("\nListado de Estudios de Animacion:")
                            for (estudioAnimacion in estudiosAnimacion) {
                                println("ID del Estudio: ${estudioAnimacion.idEstudio}")
                                println("Nombre del Estudio: ${estudioAnimacion.nombreEstudio}")
                                println("Direccion: ${estudioAnimacion.direccion}")
                                println("Numero de Animadores: ${estudioAnimacion.numeroAnimadores}")
                                println("Presupuesto: ${estudioAnimacion.presupuesto}\n")
                            }
                        }

                        "3" -> {
                            print("\nIngresa el nombre del Estudio de Animacion a actualizar: ")
                            val actualizarEstudio = readLine().toString()
                            val estudioActualizado = estudiosAnimacion.firstOrNull {
                                it.nombreEstudio.equals(
                                    actualizarEstudio,
                                    ignoreCase = true
                                )
                            }
                            if (estudioActualizado != null) {
                                println("\nEstudio de Animacion encontrado " +
                                        "\n Ingrese los nuevos datos:")
                                val estudioAnimacionActualizado = ingresarEstudio()
                                val index = estudiosAnimacion.indexOf(estudioActualizado)
                                estudiosAnimacion[index] = estudioAnimacionActualizado
                                println("\nEstudio de Animacion actualizado exitosamente.\n")

                                val content = estudiosAnimacion.joinToString("\n") { it.toString() }
                                fileManager.saveTextFile(
                                    fileName = pathEstudioAnimacion + "$estudioAnimacionActualizado.txt",
                                    content = content
                                )
                                println("\nEl Estudio de Animacion se ha guardado en la lista.\n")
                            } else {
                                println("\nNo se encontró ningún Estudio de Animacion\n" +
                                        "Intenta con otro nombre\n")
                            }
                        }

                        "4" -> {
                            print("\nIngresa el nombre del Estudio de Animacion a eliminar: ")
                            val eliminarEstudio = readLine().toString()
                            val resultadoEliminacion = estudiosAnimacion.removeIf { it.nombreEstudio.equals(eliminarEstudio, ignoreCase = true) }
                            if (resultadoEliminacion) {
                                println("\nEstudio de Animacion eliminado\n")
                            } else {
                                println("\nNo se encontró ningún Estudio de Animacion\n")
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
                            fileManager.saveTextFile(fileName = pathPersonajes+"$personaje.txt", content = content)
                            println("\nLa lista de personajes se ha guardado\n")
                        }
                        "2" -> {
                            println("\nListado de Personajes:")
                            for (personaje in personajes) {
                                println("ID del Personaje: ${personaje.idPersonaje}")
                                println("Nombre del Personaje: ${personaje.nombrePersonaje}")
                                println("Edad del Personaje: ${personaje.edadPersonaje}")
                                println("Estatura: ${personaje.estarura}")
                                println("Peso: ${personaje.peso}")

                            }
                        }

                        "3" -> {
                            print("\nIngresa el nombre del Personaje a actualizar: ")
                            val actualizarPersonaje = readLine().toString()
                            val personajeActualizado = personajes.firstOrNull {
                                it.nombrePersonaje.equals(
                                    actualizarPersonaje,
                                    ignoreCase = true
                                )
                            }
                            if (personajeActualizado != null) {
                                println("\nEstudio de Animacion encontrado " +
                                        "\n Ingrese los nuevos datos:")
                                val personajeAnimacionActualizado = ingresarPersonaje()
                                val index = personajes.indexOf(personajeActualizado)
                                personajes[index] = personajeAnimacionActualizado
                                println("\nEstudio de Animacion actualizado exitosamente.\n")

                                val content = personajes.joinToString("\n") { it.toString() }
                                fileManager.saveTextFile(
                                    fileName = pathPersonajes + "$personajeAnimacionActualizado.txt",
                                    content = content
                                )
                                println("\nEl Estudio de Animacion se ha guardado en la lista.\n")
                            } else {
                                println("\nNo se encontró ningún Estudio de Animacion\n")
                            }
                        }

                        "4" -> {
                            print("\nIngresa el nombre del Personaje a eliminar: ")
                            val eliminarPersonaje = readLine().toString()
                            val resultadoEliminacion = personajes.removeIf { it.nombrePersonaje.equals(eliminarPersonaje, ignoreCase = true) }
                            if (resultadoEliminacion) {
                                println("\nPersonaje eliminado exitosamente.\n")
                            } else {
                                println("\nNo se encontró ningún Personaje con ese nombre.\n")
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

fun ingresarEstudio(): EstudioAnimacion{
    println("\n--- Nuevo Estudio de Animacion ---\n")
    print("Ingresa un ID del Estudio: ")
    val idEstudio = readLine()?.toInt()
    print("Ingresa el Nombre del Estudio: ")
    val nombreEstudio = readLine().toString()
    print("Ingresa la Direccion del Estudio: ")
    val direccion = readLine().toString()
    print("Ingresa el Numero de Animadores del Estudio: ")
    val numeroAnimadores = readLine()?.toInt()
    print("Ingresa el Presupuesto total del Estudio: ")
    val presupuesto = readLine()?.toDouble()
    return EstudioAnimacion( idEstudio,nombreEstudio,direccion,numeroAnimadores ?: 0,  presupuesto ?: 0.0)
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
