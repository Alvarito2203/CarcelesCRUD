package com.example.crudusuario.service;



import org.springframework.stereotype.Service;
import java.util.List;
import com.example.crudusuario.model.Equipo;
import com.example.crudusuario.repository.EquipoRepository;

@Service
public class EquipoService {
    private final EquipoRepository equipoRepository;

    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public List<Equipo> listarEquipos() {
        return equipoRepository.findAll();
    }

    public Equipo obtenerEquipoPorId(Long id) {
        return equipoRepository.findById(id).orElse(null);
    }

    public void agregarEquipo(Equipo equipo) {
        equipoRepository.save(equipo);
    }

    public void actualizarEquipo(Long id, Equipo equipo) {
        if (equipoRepository.existsById(id)) {
            equipo.setId(id);
            equipoRepository.save(equipo);
        }
    }

    public void eliminarEquipo(Long id) {
        equipoRepository.deleteById(id);
    }
}
