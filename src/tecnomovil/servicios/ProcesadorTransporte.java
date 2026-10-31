package tecnomovil.servicios;

import tecnomovil.modelo.Registro;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
}