package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.ListPositionDTO;
import com.sena.crud_basic.interfaces.IPosition;
import com.sena.crud_basic.model.position;

@Service
public class positionServices {

    @Autowired
    private IPosition positionRepository;

    // Obtener todas las posiciones (convertidas a DTO)
    public List<ListPositionDTO> getAllPositions() {
        try {
            var positionsList = positionRepository.findAll();
            return positionsList.stream()
                    .map(this::convertPositionToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las posiciones: " + e.getMessage(), e);
        }
    }

    // Obtener una posición por ID (opcional)
    public Optional<ListPositionDTO> getPositionById(int id) {
        try {
            return positionRepository.findById(id)
                    .map(this::convertPositionToDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la posición por ID: " + e.getMessage(), e);
        }
    }

    // Guardar una nueva posición
    public ListPositionDTO savePosition(ListPositionDTO positionDTO) {
        try {
            if (positionDTO == null) {
                throw new IllegalArgumentException("La posición no puede ser null.");
            }
            var newPosition = convertDTOToPosition(positionDTO);
            positionRepository.save(newPosition);
            return convertPositionToDTO(newPosition);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la posición: " + e.getMessage(), e);
        }
    }

    // Actualizar una posición existente
    public ListPositionDTO updatePosition(int id, ListPositionDTO positionDTO) {
        try {
            if (positionDTO == null) {
                throw new IllegalArgumentException("La posición no puede ser null.");
            }

            var optionalPosition = getPositionById(id);
            if (optionalPosition.isPresent()) {
 // Asegura mantener el ID original
                var newPosition = convertDTOToPosition(positionDTO);
                newPosition.setIdPosition(id);
                positionRepository.save(newPosition);
                return convertPositionToDTO(newPosition);
            } else {
                throw new RuntimeException("Posición con ID " + id + " no encontrada.");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la posición: " + e.getMessage(), e);
        }
    }

    // Eliminar una posición por ID
    public void deletePosition(int id) {
        try {
            var optionalPosition = positionRepository.findById(id);
            if (optionalPosition.isPresent()) {
                positionRepository.deleteById(id);
            } else {
                throw new RuntimeException("Posición con ID " + id + " no encontrada para eliminar.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la posición: " + e.getMessage(), e);
        }
    }

    // Filtrar posiciones por nombre
    public List<ListPositionDTO> filterPositions(String namePosition) {
        try {
            var filteredList = positionRepository.filterPositions(namePosition);
            return filteredList.stream()
                    .map(this::convertPositionToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al filtrar las posiciones: " + e.getMessage(), e);
        }
    }

    // Convertir entidad a DTO
    public ListPositionDTO convertPositionToDTO(position position) {
        return new ListPositionDTO(
            
                position.getIdPosition(),
                position.getNamePosition()
        );
    }

    public position convertDTOToPosition(ListPositionDTO positionDTO) {
        return new position(
                0,
                positionDTO.getNamePosition(),
                null
        );
    }
}
