package com.crud.crud.repositorio;

import com.crud.crud.modelo.Ciudadano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadanoRepositorio extends JpaRepository<Ciudadano, Long> {
}
