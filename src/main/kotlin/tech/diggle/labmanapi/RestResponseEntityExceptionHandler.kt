package tech.diggle.labmanapi

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [(IllegalArgumentException::class), (IllegalStateException::class)])
    protected fun handleConflict(ex: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        val bodyOfResponse = ex.message
        return handleExceptionInternal(ex, bodyOfResponse, HttpHeaders(), HttpStatus.CONFLICT, request)
    }

    @ExceptionHandler(value = [(BadCredentialsException::class)])
    protected fun invalidCredentials(ex: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        val bodyOfResponse = "Invalid credentials"
        return handleExceptionInternal(ex, bodyOfResponse, HttpHeaders(), HttpStatus.UNAUTHORIZED, request)
    }


//    @ExceptionHandler(value = [(NullPointerException::class)])
    protected fun nullPointer(ex: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        val bodyOfResponse = ex.message
        return handleExceptionInternal(ex, bodyOfResponse, HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }



//    @ExceptionHandler(value = *arrayOf(RuntimeException::class))
//    protected fun exceptionHandler(ex: RuntimeException, request: WebRequest): ResponseEntity<Any>{
//        val bodyOfResponse = ex.message
//        return handleExceptionInternal(ex, bodyOfResponse, HttpHeaders(), HttpStatus.BAD_REQUEST, request)
//    }
}
