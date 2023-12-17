package com.api.downupload.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import com.api.downupload.model.Documento;

public interface IFileSytemStorage {
	void init();
	Documento saveFile(MultipartFile file);
	Resource loadFile(Long idDocumento);
}
