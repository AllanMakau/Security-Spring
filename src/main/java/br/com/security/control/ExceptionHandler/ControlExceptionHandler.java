package br.com.security.control.ExceptionHandler;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControlExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String msgErrorUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String msgErrorDev = ex.getCause().toString();
		List<Error> erros = Arrays.asList(new Error(msgErrorUser, msgErrorDev));
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request) ;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Error> erros = this.criaListaErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request) ;
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	protected ResponseEntity<Object> handleEmptyResultDataAccessException( EmptyResultDataAccessException ex, WebRequest request) {
		String msgErrorUser = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String msgErrorDev = ex.toString();
		List<Error> erros = Arrays.asList(new Error(msgErrorUser, msgErrorDev));
		return handleExceptionInternal(ex, erros,new HttpHeaders(), HttpStatus.NOT_FOUND, request) ; 
	}
	
	private List<Error> criaListaErros(BindingResult bindingResult){
		List<Error> erros = new ArrayList<>();
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			String msgErrorUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()) ;
			String msgErrorDev = fieldError.toString() ;
			erros.add(new Error(msgErrorUser, msgErrorDev));
		}
		return erros;
	}
	
	
	public static class Error {
		
		private  String msgErrorUser;
		private  String msgErrorDev;
		
		public Error(String msgErrorUser,String msgErrorDev) {
			this.msgErrorUser = msgErrorUser;
			this.msgErrorDev = msgErrorDev;
		}

		public String getMsgErrorUser() {
			return msgErrorUser;
		}

		public void setMsgErrorUser(String msgErrorUser) {
			this.msgErrorUser = msgErrorUser;
		}

		public String getMsgErrorDev() {
			return msgErrorDev;
		}

		public void setMsgErrorDev(String msgErrorDev) {
			this.msgErrorDev = msgErrorDev;
		}
		
		
		
	}
	

}


