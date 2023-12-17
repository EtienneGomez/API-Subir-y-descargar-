package com.api.downupload.controller;
import com.api.downupload.model.Documento;


import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.downupload.model.FileResponse;
import com.api.downupload.service.IFileSytemStorage;

@RestController
@RequestMapping("/api")
// Importaciones y anotaciones de clase...

public class FileController {

	@Autowired
	IFileSytemStorage fileSytemStorage;

	@PostMapping("/uploadfile")
	public ResponseEntity<FileResponse> uploadSingleFile(@RequestParam("file") MultipartFile file) {
		Documento documento = fileSytemStorage.saveFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/download/")
				.path(documento.getIdDocumento().toString())
				.toUriString();

		return ResponseEntity.status(HttpStatus.OK)
				.body(new FileResponse(documento.getNombreDocumento(), fileDownloadUri, "File uploaded with success!"));
	}

	@PostMapping("/uploadfiles")
	public ResponseEntity<List<FileResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		List<FileResponse> responses = Arrays.stream(files)
				.map(file -> {
					Documento documento = fileSytemStorage.saveFile(file);
					String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("/api/download/")
							.path(documento.getIdDocumento().toString())
							.toUriString();
					return new FileResponse(documento.getNombreDocumento(), fileDownloadUri, "File uploaded with success!");
				})
				.collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(responses);
	}

	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
		Resource resource = fileSytemStorage.loadFile(id);
		String filename = "downloaded_file"; // Puedes obtener el nombre del archivo de otra manera si es necesario

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
				.body(resource);
	}
}
