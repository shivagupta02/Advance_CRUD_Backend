package com.OnlyApp.OnlyApp.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.OnlyApp.OnlyApp.Models.ApiResponce;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> resourceNotFound(ResourceNotFoundException ex)
	{
		String message= ex.getMessage();
		ApiResponce apiResponse= new ApiResponce(message, false);
		return new ResponseEntity<ApiResponce>(apiResponse, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String, String> resp= new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->
		{
			String fieldName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(resp, HttpStatus.BAD_REQUEST);
	}
//	  @ExceptionHandler(IllegalArgumentException.class)
//	    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Illegal argument: " + ex.getMessage());
//	    }
}
