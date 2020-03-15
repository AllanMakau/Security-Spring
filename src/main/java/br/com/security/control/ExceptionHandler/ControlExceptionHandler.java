package br.com.security.control.ExceptionHandler;




import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.security.control.DTO.ProblemaDTO;
import br.com.security.control.DTO.TipoProblemaEnum;



@ControllerAdvice
public class ControlExceptionHandler extends ResponseEntityExceptionHandler {

	//@Autowired
	//private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Throwable rootCause =  org.apache.commons.lang3.exception.ExceptionUtils.getRootCause(ex);
		
		if(rootCause instanceof  InvalidFormatException) {
			
			return handleIvalidFormatException((InvalidFormatException) rootCause,headers,status,request);
		}
		
		TipoProblemaEnum tipo = TipoProblemaEnum.MENSAGEM_INVALIDA;
		String detalhe = "O corpo do objeto está inválido.";
		ProblemaDTO problema = new ProblemaDTO(status.value(), detalhe, tipo.getUri(), ex.getMessage());
		return handleExceptionInternal(ex, problema, headers, status, request) ;
	}

	private ResponseEntity<Object> handleIvalidFormatException(InvalidFormatException rootCause, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String path = rootCause.getPath().stream()
					  .map(ref -> ref.getFieldName())
					  .collect(Collectors.joining("."));
		
		TipoProblemaEnum tipo = TipoProblemaEnum.MENSAGEM_INVALIDA;
		String detalhe = String.format("A propriedade '%s' recebeu o valor '%s' , que é de um tipo inválido. Corrija e informe um valor com o tipo '%s' .", path,rootCause.getValue(), rootCause.getTargetType().getSimpleName());
		ProblemaDTO problema = new ProblemaDTO(status.value(), detalhe, tipo.getUri(), rootCause.getMessage());
		return handleExceptionInternal(rootCause, problema,new HttpHeaders(), status, request) ;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
		TipoProblemaEnum tipo = TipoProblemaEnum.ARGUMENTO_INVALIDO;
		ProblemaDTO problema = new ProblemaDTO(status.value(), tipo.getTitulo(), tipo.getUri(), ex.getMessage());
		return handleExceptionInternal(ex, problema, headers, HttpStatus.BAD_REQUEST, request) ;
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	protected ResponseEntity<Object> handleEmptyResultDataAccessException( EmptyResultDataAccessException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		TipoProblemaEnum tipo = TipoProblemaEnum.RECURSO_NAO_ENCONTRADO;
		ProblemaDTO problema = new ProblemaDTO(status.value(), tipo.getTitulo(), tipo.getUri(), ex.getMessage());
		return handleExceptionInternal(ex, problema,new HttpHeaders(), status, request) ; 
	}
	
	@ExceptionHandler({NoSuchElementException.class})
	protected ResponseEntity<Object> handleNoSuchElementException( NoSuchElementException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		TipoProblemaEnum tipo = TipoProblemaEnum.RECURSO_NAO_ENCONTRADO;
		ProblemaDTO problema = new ProblemaDTO(status.value(), tipo.getTitulo(), tipo.getUri(), ex.getMessage());
		return handleExceptionInternal(ex, problema,new HttpHeaders(), status, request) ; 
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class})
	protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		TipoProblemaEnum tipo = TipoProblemaEnum.ERRO_DATA_INTEGRITY;
		ProblemaDTO problema = new ProblemaDTO(status.value(), tipo.getTitulo(), tipo.getUri(), ex.getMessage());
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request) ; 
	}
	
	@ExceptionHandler({AccessDeniedException.class})
	protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
		HttpStatus status = HttpStatus.FORBIDDEN;
		TipoProblemaEnum tipo = TipoProblemaEnum.ACCESS_DANIED_EXCEPTION;
		ProblemaDTO problema = new ProblemaDTO(status.value(), tipo.getTitulo(), tipo.getUri(), ex.getMessage());
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request) ; 
	}

}


