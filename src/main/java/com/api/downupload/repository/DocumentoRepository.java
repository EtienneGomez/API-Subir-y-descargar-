package com.api.downupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.downupload.model.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
