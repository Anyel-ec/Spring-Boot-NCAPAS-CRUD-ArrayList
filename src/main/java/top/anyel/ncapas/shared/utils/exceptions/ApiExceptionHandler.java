package top.anyel.ncapas.shared.utils.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static top.anyel.ncapas.shared.utils.exceptions.ApiErrorResponse.*;

@RestControllerAdvice
@Slf4j
class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                          HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return badRequest("Solicitud JSON mal formada");
    }

    @Override
    protected ResponseEntity handleTypeMismatch(TypeMismatchException ex,
                                                HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return badRequest("Solicitud de no coincidencia de tipos");
    }

    @Override
    protected ResponseEntity handleNoHandlerFoundException(NoHandlerFoundException exception,
                                                           HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return notFound("Recurso '" + exception.getRequestURL() + "' no se encuentra");
    }

    @Override
    protected ResponseEntity handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception,
                                                                 HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var supportedMethods = exception.getSupportedHttpMethods();

        if (!CollectionUtils.isEmpty(supportedMethods)) {
            headers.setAllow(supportedMethods);
        }

        return methodNotAllowed(headers, "Método de solicitud '" + exception.getMethod() + "' no soportado");
    }

    @Override
    protected ResponseEntity handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException exception,
                                                              HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return notAcceptable("No se pudo encontrar una representación aceptable");
    }

    @Override
    protected ResponseEntity handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException exception,
                                                             HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var mediaTypes = exception.getSupportedMediaTypes();
        if (!CollectionUtils.isEmpty(mediaTypes)) {
            headers.setAccept(mediaTypes);
        }

        return unsupportedMediaType(headers, "Tipo de contenido '" + exception.getContentType() + "' no soportado");
    }

    @ExceptionHandler(Throwable.class)
    ResponseEntity<ApiErrorResponse> handleThrowable(Throwable throwable) {
        log.error("Request handling failed", throwable);
        return internalServerError("ocurrió un error inesperado");
    }
}