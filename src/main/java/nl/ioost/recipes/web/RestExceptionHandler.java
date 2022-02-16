package nl.ioost.recipes.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import nl.ioost.recipes.facade.RecipeTagNotFoundException;
import nl.ioost.recipes.model.ErrorResponse;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = RecipeTagNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleConflict(RuntimeException runtimeException, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setErrorResponseId(findErrorResponseId(webRequest));
        errorResponse.setCode(getErrorCode(runtimeException));
        errorResponse.setMessage(runtimeException.getMessage());
        errorResponse.setInvolvedProperties(webRequest.getDescription(true));

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    private Integer findErrorResponseId(WebRequest webRequest) {

        //Should return some unique integer based on webRequest

        return webRequest.hashCode();
    }

    private String getErrorCode(RuntimeException exception) {

        //These codes can probably be mapped in some xml config or anything

        if (exception instanceof RecipeTagNotFoundException) {
            return "TAG NOT PRESENT";
        } else {
            return "JUST SOME REGULAR RUNTIME EXCEPTION";
        }
    }

}