package com.api.downupload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileStorageException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	private Throwable cause; // Agregar esto si quieres pasar una excepción como causa

	public FileStorageException(String message) {
		super(message);
	}

	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
