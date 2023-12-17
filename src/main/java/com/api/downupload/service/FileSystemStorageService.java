package com.api.downupload.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.downupload.exception.FileNotFoundException;
import com.api.downupload.exception.FileStorageException;
import com.api.downupload.model.Documento;
import com.api.downupload.repository.DocumentoRepository;

@Service
public class FileSystemStorageService implements IFileSytemStorage {

	@Autowired
	private DocumentoRepository documentoRepository;

	@Override
	public Documento saveFile(MultipartFile file) {
		try {
			Documento documento = new Documento();
			documento.setDatosDocumento(file.getBytes());
			documento.setNombreDocumento(file.getOriginalFilename());
			documento.setTipoDocumento(file.getContentType());

			return documentoRepository.save(documento);
		} catch (IOException e) {
			throw new FileStorageException("Could not store file " + file.getOriginalFilename(), e);
		}
	}

	@Override
	public Resource loadFile(Long idDocumento) {
		Documento documento = documentoRepository.findById(idDocumento)
				.orElseThrow(() -> new FileNotFoundException("File not found with id " + idDocumento));

		return new ByteArrayResource(documento.getDatosDocumento());
	}
}
