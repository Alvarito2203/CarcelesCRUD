package com.example.crudusuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crudusuario.model.Futbolista;
import java.util.List;

public interface FutbolistaRepository extends JpaRepository<Futbolista, Long> {
    List<Futbolista> findByEquipoId(Long equipoId);
}
