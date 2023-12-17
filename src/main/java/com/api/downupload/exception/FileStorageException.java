package com.api.downupload.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileStorageException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;

	public FileStorageException(String message) {
		super(message);
		this.message = message;
	}

	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}
}
