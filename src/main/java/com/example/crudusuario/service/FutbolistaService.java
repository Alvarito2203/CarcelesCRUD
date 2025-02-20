package com.example.crudusuario.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.crudusuario.model.Futbolista;
import com.example.crudusuario.repository.FutbolistaRepository;

@Service
public class FutbolistaService {
    private final FutbolistaRepository futbolistaRepository;

    public FutbolistaService(FutbolistaRepository futbolistaRepository) {
        this.futbolistaRepository = futbolistaRepository;
    }

    public List<Futbolista> listarFutbolistas() {
        return futbolistaRepository.findAll();
    }

    public Futbolista obtenerFutbolistaPorId(Long id) {
        return futbolistaRepository.findById(id).orElse(null);
    }

    public void agregarFutbolista(Futbolista futbolista) {
        futbolistaRepository.save(futbolista);
    }

    public void actualizarFutbolista(Long id, Futbolista futbolista) {
        if (futbolistaRepository.existsById(id)) {
            futbolista.setId(id);
            futbolistaRepository.save(futbolista);
        }
    }

    public void eliminarFutbolista(Long id) {
        futbolistaRepository.deleteById(id);
    }
}

