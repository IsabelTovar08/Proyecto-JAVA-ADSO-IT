package com.sena.crud_basic.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.ListServiceDTO;
import com.sena.crud_basic.DTO.RegistrerCategoryDTO;
import com.sena.crud_basic.DTO.RegistrerServiceDTO;
import com.sena.crud_basic.interfaces.ICategory;
import com.sena.crud_basic.model.category;

@Service
public class categoryServices {
    
    @Autowired
    private ICategory categoryData;

    // Obtener todas las categorías convertidas a DTO
    public List<RegistrerCategoryDTO> getAllCategories() {
        try {
            var categories = categoryData.findAll();
            return categories.stream()
                    .map(this::convertCategoryToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener todas las categorías: " + e.getMessage(), e);
        }
    }

    // Obtener una categoría por ID
    public Optional<RegistrerCategoryDTO> getCategoryById(int id) {
        try {
            return categoryData.findById(id)
                    .map(this::convertCategoryToDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la categoría por ID: " + e.getMessage(), e);
        }
    }

    // Guardar una nueva categoría desde un DTO
    public RegistrerCategoryDTO saveCategory(RegistrerCategoryDTO categoryDTO) {
        try {
            if (categoryDTO == null) {
                throw new IllegalArgumentException("La categoría no puede ser null.");
            }
            categoryData.save(convertDTOToCategory(categoryDTO));
            return categoryDTO;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la categoría: " + e.getMessage(), e);
        }
    }

    // Actualizar una categoría existente
    public RegistrerCategoryDTO updateCategory(int id, RegistrerCategoryDTO categoryDTO) {
        try {
            if (categoryDTO == null) {
                throw new IllegalArgumentException("La categoría no puede ser null.");
            }
            var categoryIsFound = categoryData.findById(id);
            if (categoryIsFound.isPresent()) {
                var updatedCategory = convertDTOToCategory(categoryDTO);
                updatedCategory.setIdCategory(id);
                categoryData.save(updatedCategory);
                return categoryDTO;
            } else {
                throw new RuntimeException("Categoría con ID " + id + " no encontrada.");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la categoría: " + e.getMessage(), e);
        }
    }

    // Eliminar una categoría
    public void deleteCategory(int id) {
        try {
            var category = categoryData.findById(id);
            if (category.isPresent()) {
                categoryData.deleteById(id);
            } else {
                throw new RuntimeException("Categoría con ID " + id + " no encontrada para eliminar.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la categoría: " + e.getMessage(), e);
        }
    }


    // Método para convertir DTO a Entidad
    private category convertDTOToCategory(RegistrerCategoryDTO categoryDTO) {
        return new category(
            0, // El ID se asigna automáticamente al guardar
            categoryDTO.getNameCategory(), 
            null
        );
    }

    // Método para convertir Entidad a DTO
    private RegistrerCategoryDTO convertCategoryToDTO(category categoryEntity) {
        return new RegistrerCategoryDTO(
            categoryEntity.getIdCategory(),
            categoryEntity.getNameCategory(),
            categoryEntity.getServices().stream()
                .map(service -> new ListServiceDTO(
                    service.getIdService(),
                    service.getNameService(),
                    service.getDescriptionService(),
                    service.getBasePriceService(),
                    service.getCategory().getIdCategory(),
                    service.getCategory().getNameCategory()
                     // Aquí puedes asignar la categoría si es necesario
                ))
                .collect(Collectors.toList())
        );
    }
}
