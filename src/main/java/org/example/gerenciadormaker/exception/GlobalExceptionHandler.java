package org.example.gerenciadormaker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice //ouvinte global de erros da aplicação ouvinte global de erros da aplicação.
public class GlobalExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class) //“Você me passou um argumento inválido”
    public ResponseEntity<String> handleNotFound(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) //estamos tratando erro de recurso inexistente
                .body(ex.getMessage());//cliente recebe mensagem lançada no service
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalStateException.class) //“O estado do sistema não permite essa operação”
    public ResponseEntity<String> handleConflict(IllegalStateException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(mensagem);
    }
}
