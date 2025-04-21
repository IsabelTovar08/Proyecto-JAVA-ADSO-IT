package com.sena.crud_basic.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.ListServiceDTO;
import com.sena.crud_basic.DTO.RegistrerCategoryDTO;
import com.sena.crud_basic.DTO.RegistrerServiceDTO;
import com.sena.crud_basic.interfaces.IServiceRepository;
import com.sena.crud_basic.model.category;
import com.sena.crud_basic.model.service;

@Service
public class serviceServices {

    @Autowired
    private IServiceRepository serviceRepository;

    // Obtener todos los servicios (convertidos a DTO)
    public List<ListServiceDTO> getAllServices() {
        try {
            var servicesList = serviceRepository.findAll();
            return servicesList.stream()
                    .map(this::convertServiceToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los servicios: " + e.getMessage(), e);
        }
    }

    // Obtener un servicio por ID (opcional)
    public Optional<ListServiceDTO> getServiceById(int id) {
        try {
            return serviceRepository.findById(id)
                    .map(this::convertServiceToDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el servicio por ID: " + e.getMessage(), e);
        }
    }

    // Guardar un nuevo servicio
    public ListServiceDTO saveService(RegistrerServiceDTO serviceDTO) {
        try {
            if (serviceDTO == null) {
                throw new IllegalArgumentException("El servicio no puede ser null.");
            }

            service newService = convertDTOToService(serviceDTO);
            serviceRepository.save(newService);
            return convertServiceToDTO(newService);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el servicio: " + e.getMessage(), e);
        }
    }

    // Actualizar un servicio existente
    public ListServiceDTO updateService(int id, RegistrerServiceDTO serviceDTO) {
        try {
            if (serviceDTO == null) {
                throw new IllegalArgumentException("El servicio no puede ser null.");
            }

            var optionalService = serviceRepository.findById(id);
            if (optionalService.isPresent()) {
                service serviceUpdate = convertDTOToService(serviceDTO);
                serviceUpdate.setIdService(id); // Asegura mantener el ID original
                serviceRepository.save(serviceUpdate);
                return convertServiceToDTO(serviceUpdate);
            } else {
                throw new RuntimeException("Servicio con ID " + id + " no encontrado.");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el servicio: " + e.getMessage(), e);
        }
    }

    // Eliminar un servicio por ID
    public void deleteService(int id) {
        try {
            var optionalService = serviceRepository.findById(id);
            if (optionalService.isPresent()) {
                serviceRepository.deleteById(id);
            } else {
                throw new RuntimeException("Servicio con ID " + id + " no encontrado para eliminar.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el servicio: " + e.getMessage(), e);
        }
    }

    // Filtrar servicios por nombre
public List<ListServiceDTO> filterServices(String name, Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice) {
    try {
        var filteredList = serviceRepository.filterServices(name, categoryId, minPrice, maxPrice);
        return filteredList.stream()
                .map(this::convertServiceToDTO)
                .collect(Collectors.toList());
    } catch (Exception e) {
        throw new RuntimeException("Error al filtrar los servicios: " + e.getMessage(), e);
    }
}



    // Convertir DTO a entidad
    public service convertDTOToService(RegistrerServiceDTO serviceDTO) {

        category category = new category();
        category.setIdCategory(serviceDTO.getIdCategory());

        return new service(
                0,
                serviceDTO.getNameService(),
                serviceDTO.getDescriptionService(),
                serviceDTO.getBasePriceService(),
                category
        );

    }

    // Convertir entidad a DTO
    public ListServiceDTO convertServiceToDTO(service service) {

        return new ListServiceDTO(
                service.getIdService(), // corregido: antes estaba fijo en 0
                service.getNameService(),
                service.getDescriptionService(),
                service.getBasePriceService(),
                service.getCategory().getIdCategory(),
                service.getCategory().getNameCategory()
        );
    }
}
