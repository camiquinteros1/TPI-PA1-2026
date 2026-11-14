
package ar.edu.undec.adapter.handler;

import empleado.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmpleadoNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> empleadoNoEncontrado(
            EmpleadoNoEncontradoException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(LegajoDuplicadoException.class)
    public ResponseEntity<Map<String, String>> legajoDuplicado(
            LegajoDuplicadoException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler({
            CampoObligatorioException.class,
            FechaIngresoFuturaException.class,
            FormatoLegajoInvalidoException.class,
            SalarioInferiorAlPisoException.class,
            SalarioInvalidoException.class
    })
    public ResponseEntity<Map<String, String>> errorDeValidacion(
            RuntimeException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", ex.getMessage()));
    }
}
