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
                        LocalDateTime.of(2026, 10, 30, 8, 45))
        );
    }
}
