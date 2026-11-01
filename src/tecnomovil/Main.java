package tecnomovil;

import tecnomovil.datos.DatosSimulados;
import tecnomovil.modelo.Registro;
import tecnomovil.servicios.ProcesadorTransporte;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // Obtener los registros simulados
        List<Registro> registros =
                DatosSimulados.generarRegistros();

        // Establecer el umbral para detectar rutas críticas
        long umbral = 1;

        // REQUERIMIENTO 1: Afluencia por estación
        Map<String, Long> afluencia =
                ProcesadorTransporte.calcularAfluenciaPorEstacion(
                        registros
                );

        // REQUERIMIENTO 2: Identificación de horas pico
        Map<Integer, Long> horasPico =
                ProcesadorTransporte.identificarHorasPico(
                        registros
                );

        // REQUERIMIENTO 3: Rutas más utilizadas
        Map<String, Long> rutas =
                ProcesadorTransporte.rutasMasUtilizadas(
                        registros
                );

        // REQUERIMIENTO 4: Patrones de viaje
        Map<String, List<String>> patrones =
                ProcesadorTransporte.obtenerPatronesDeViaje(
                        registros
                );

        // REQUERIMIENTO 5: Tiempo promedio entre estaciones
        double tiempoPromedio =
                ProcesadorTransporte.calcularTiempoPromedioEntreEstaciones(
                        registros
                );

        // REQUERIMIENTO 6: Detección de rutas críticas
        Map<String, Long> rutasCriticas =
                ProcesadorTransporte.detectarRutasCriticas(
                        registros,
                        umbral
                );


        // MOSTRAR LOS RESULTADOS

        System.out.println("========================================");
        System.out.println("          TECNOMOVIL DATA");
        System.out.println("========================================");

        System.out.println("\nTOTAL DE REGISTROS: " + registros.size());


        // RESULTADO 1
        System.out.println("\n1. AFLUENCIA POR ESTACION");
        System.out.println("----------------------------------------");

        afluencia.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entrada ->
                        System.out.println(
                                entrada.getKey() + ": "
                                        + entrada.getValue()
                                        + " pasajeros"
                        )
                );


        // RESULTADO 2
        System.out.println("\n2. IDENTIFICACION DE HORAS PICO");
        System.out.println("----------------------------------------");

        horasPico.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entrada ->
                        System.out.println(
                                entrada.getKey() + ":00 - "
                                        + entrada.getValue()
                                        + " pasajeros"
                        )
                );


        // RESULTADO 3
        System.out.println("\n3. RUTAS MAS UTILIZADAS");
        System.out.println("----------------------------------------");

        rutas.entrySet().stream()
                .sorted(
                        Map.Entry.<String, Long>
                                comparingByValue().reversed()
                )
                .forEach(entrada ->
                        System.out.println(
                                entrada.getKey() + ": "
                                        + entrada.getValue()
                                        + " pasajeros"
                        )
                );


        // RESULTADO 4
        System.out.println("\n4. PATRONES DE VIAJE POR USUARIO");
        System.out.println("----------------------------------------");

        patrones.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entrada ->
                        System.out.println(
                                entrada.getKey() + " -> "
                                        + entrada.getValue()
                        )
                );


        // RESULTADO 5
        System.out.println("\n5. TIEMPO PROMEDIO ENTRE ESTACIONES");
        System.out.println("----------------------------------------");

        System.out.printf(
                "Tiempo promedio: %.2f minutos%n",
                tiempoPromedio
        );


        // RESULTADO 6
        System.out.println("\n6. DETECCION DE RUTAS CRITICAS");
        System.out.println("----------------------------------------");

        System.out.println("Umbral establecido: " + umbral);

        if (rutasCriticas.isEmpty()) {

            System.out.println("No se detectaron rutas criticas.");

        } else {

            rutasCriticas.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entrada ->
                            System.out.println(
                                    entrada.getKey() + ": "
                                            + entrada.getValue()
                                            + " pasajeros - CRITICA"
                            )
                    );
        }

        System.out.println("\n========================================");
        System.out.println("       FIN DEL PROCESAMIENTO");
        System.out.println("========================================");
    }
}
