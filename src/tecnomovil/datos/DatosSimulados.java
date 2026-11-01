package tecnomovil.datos;

import tecnomovil.modelo.Registro;

import java.time.LocalDateTime;
import java.util.List;

public class DatosSimulados {

    public static List<Registro> generarRegistros() {

        return List.of(

                new Registro("U001", "RUTA01", "Centro", "entrada",
                        LocalDateTime.of(2026, 10, 30, 7, 0)),

                new Registro("U002", "RUTA01", "Centro", "entrada",
                        LocalDateTime.of(2026, 10, 30, 7, 15)),

                new Registro("U003", "RUTA02", "Norte", "entrada",
                        LocalDateTime.of(2026, 10, 30, 8, 0)),

                new Registro("U001", "RUTA01", "Sur", "salida",
                        LocalDateTime.of(2026, 10, 30, 7, 30)),

                new Registro("U004", "RUTA02", "Norte", "entrada",
                        LocalDateTime.of(2026, 10, 30, 8, 15)),

                new Registro("U002", "RUTA01", "Sur", "salida",
                        LocalDateTime.of(2026, 10, 30, 7, 45)),

                new Registro("U005", "RUTA03", "Centro", "entrada",
                        LocalDateTime.of(2026, 10, 30, 9, 0)),

                new Registro("U003", "RUTA02", "Sur", "salida",
                        LocalDateTime.of(2026, 10, 30, 8, 45)),

                new Registro("U009", "RUTA01", "Mercado", "entrada",
                        LocalDateTime.of(2026, 10, 30, 9, 0)),

                new Registro("U009", "RUTA01", "Centro", "salida",
                        LocalDateTime.of(2026, 10, 30, 9, 35)),

                new Registro("U010", "RUTA02", "Sur", "entrada",
                        LocalDateTime.of(2026, 10, 30, 9, 15)),

                new Registro("U010", "RUTA02", "Terminal", "salida",
                        LocalDateTime.of(2026, 10, 30, 10, 0)),

                new Registro("U011", "RUTA03", "Centro", "entrada",
                        LocalDateTime.of(2026, 10, 30, 10, 0)),

                new Registro("U011", "RUTA03", "Universidad", "salida",
                        LocalDateTime.of(2026, 10, 30, 10, 40)),

                new Registro("U012", "RUTA04", "Norte", "entrada",
                        LocalDateTime.of(2026, 10, 30, 10, 15)),

                new Registro("U012", "RUTA04", "Sur", "salida",
                        LocalDateTime.of(2026, 10, 30, 11, 0))

        );
    }
}
