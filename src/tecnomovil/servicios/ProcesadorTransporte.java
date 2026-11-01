package tecnomovil.servicios;

import tecnomovil.modelo.Registro;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.Duration;
import java.util.Comparator;
import java.util.stream.IntStream;

public class ProcesadorTransporte {

    // REQUERIMIENTO 1:
    // Calcular la afluencia de pasajeros por estación.

    public static Map<String, Long> calcularAfluenciaPorEstacion(
            List<Registro> registros) {

        return Map.copyOf(
                registros.stream()

                        // Filtrar únicamente las entradas
                        .filter(registro ->
                                registro.accion().equalsIgnoreCase("entrada"))

                        // Agrupar los registros por estación
                        .collect(Collectors.groupingBy(
                                Registro::estacion,
                                Collectors.counting()
                        ))
        );
    }

    // REQUERIMIENTO 2:
    // Identificar horas pico.

    public static Map<Integer, Long> identificarHorasPico(
            List<Registro> registros) {

        return Map.copyOf(
                registros.stream()

                        // Filtrar únicamente las entradas
                        .filter(registro ->
                                registro.accion().equals("entrada"))

                        // Agrupar por hora y contar los registros
                        .collect(Collectors.groupingBy(
                                registro -> registro.timestamp().getHour(),
                                Collectors.counting()
                        ))
        );
    }

    // REQUERIMIENTO 3:
    // Identificar las rutas más utilizadas.

    public static Map<String, Long> rutasMasUtilizadas(
            List<Registro> registros) {

        return Map.copyOf(
                registros.stream()

                        // Filtrar únicamente las entradas
                        .filter(registro ->
                                registro.accion().equals("entrada"))

                        // Agrupar por ruta y contar pasajeros
                        .collect(Collectors.groupingBy(
                                Registro::ruta,
                                Collectors.counting()
                        ))
        );
    }

    // REQUERIMIENTO 4:
    // Obtener patrones de viaje por usuario.

    public static Map<String, List<String>> obtenerPatronesDeViaje(
            List<Registro> registros) {

        return Map.copyOf(
                registros.stream()

                        // Agrupar los registros por usuario
                        .collect(Collectors.groupingBy(
                                Registro::idUsuario,

                                // Procesar cada grupo de registros
                                Collectors.collectingAndThen(
                                        Collectors.toList(),

                                        lista -> lista.stream()

                                                // Ordenar cronológicamente
                                                .sorted(Comparator.comparing(
                                                        Registro::timestamp
                                                ))

                                                // Obtener las estaciones
                                                .map(Registro::estacion)

                                                // Crear una lista inmutable
                                                .toList()
                                )
                        ))
        );
    }


    // REQUERIMIENTO 5:
    // Calcular el tiempo promedio entre estaciones consecutivas.

    public static double calcularTiempoPromedioEntreEstaciones(
            List<Registro> registros) {

        return registros.stream()

                // Agrupar los registros por usuario
                .collect(Collectors.groupingBy(
                        Registro::idUsuario
                ))

                // Obtener los grupos de usuarios
                .values()
                .stream()

                // Procesar cada usuario
                .flatMapToLong(lista -> {

                    // Ordenar sus registros cronológicamente
                    List<Registro> ordenados = lista.stream()
                            .sorted(Comparator.comparing(
                                    Registro::timestamp
                            ))
                            .toList();

                    // Comparar estaciones consecutivas
                    return IntStream.range(
                                    0,
                                    ordenados.size() - 1
                            )

                            // Evitar comparar una estación consigo misma
                            .filter(i ->
                                    !ordenados.get(i).estacion().equals(
                                            ordenados.get(i + 1).estacion()
                                    )
                            )

                            // Calcular la diferencia en minutos
                            .mapToLong(i ->
                                    Duration.between(
                                            ordenados.get(i).timestamp(),
                                            ordenados.get(i + 1).timestamp()
                                    ).toMinutes()
                            );
                })

                // Calcular el promedio general
                .average()

                // Si no existen diferencias, devolver cero
                .orElse(0.0);
    }

    // REQUERIMIENTO 6:
    // Detectar rutas que superan el umbral establecido.

    public static Map<String, Long> detectarRutasCriticas(
            List<Registro> registros,
            long umbral) {

        return Map.copyOf(
                rutasMasUtilizadas(registros)
                        .entrySet()
                        .stream()

                        // Filtrar rutas que superan el umbral
                        .filter(entrada ->
                                entrada.getValue() > umbral)

                        // Obtener las rutas críticas
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue
                        ))
        );
    }

}