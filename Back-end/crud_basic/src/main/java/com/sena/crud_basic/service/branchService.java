package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.ListBranchDTO;
import com.sena.crud_basic.DTO.requestDTO.requestBranch;
import com.sena.crud_basic.interfaces.IBranch;
import com.sena.crud_basic.model.branch;
import com.sena.crud_basic.model.municipality;

@Service
public class branchService {
    @Autowired
    private IBranch branchRepository;

    public List<ListBranchDTO> getAllBranch() {
        try {
            var branchList = branchRepository.findAll();
            return branchList.stream()
                    .map(this::convertBranchToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las suscursales: " + e.getMessage(), e);
        }
    }

    public Optional<ListBranchDTO> getBranchById(int id) {
        try {
            return branchRepository.findById(id)
                    .map(this::convertBranchToDto);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el Empleado por ID: " + e.getMessage(), e);
        }
    }

    public ListBranchDTO saveBranch(requestBranch BranchDTO) {
        try {
            if (BranchDTO == null) {
                throw new IllegalArgumentException("eL Empleado no puede ser null.");
            }
            var newBranch = convertBranchToDtoCreate(BranchDTO);
            branchRepository.save(convertDtoToBranch(newBranch));
            return newBranch;

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el Empleado: " + e.getMessage(), e);
        }
    }

    public ListBranchDTO updateBranch(int id, requestBranch BranchDTO) {
        try {
            if (BranchDTO == null) {
                throw new IllegalArgumentException("eL Empleado no puede ser null.");
            }

            var optionalBranch = getBranchById(id);
            if (optionalBranch.isPresent()) {
 // Asegura mantener el ID original
                var newBranch = convertBranchToDtoCreate(BranchDTO);
                newBranch.setIdBranch(id);
                branchRepository.save(convertDtoToBranch(newBranch));
                return newBranch;
            } else {
                throw new RuntimeException("Empleado con ID " + id + " no encontrada.");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el Empleado: " + e.getMessage(), e);
        }
    }

    public void deleteBranch(int id) {
        try {
            var optionalBranch = branchRepository.findById(id);
            if (optionalBranch.isPresent()) {
                branchRepository.deleteById(id);
            } else {
                throw new RuntimeException("Empleado con ID " + id + " no encontrada para eliminar.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el Empleado: " + e.getMessage(), e);
        }
    }

    // Filtrar posiciones por nombre
    public List<ListBranchDTO> filterBranchs(String nameBranch) {
        try {
            var filteredList = branchRepository.filterBranchs(nameBranch);
            return filteredList.stream()
                    .map(this::convertBranchToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al filtrar las sucursakes: " + e.getMessage(), e);
        }
    }

    public ListBranchDTO convertBranchToDto(branch branch){
        return new ListBranchDTO(
            branch.getIdBranch(),
            branch.getNameBranch(),
            branch.getMunicipality().getIdMunicipality(),
            branch.getMunicipality().getNameMunicipality()
        );
    }

    public ListBranchDTO convertBranchToDtoCreate(requestBranch branch){
        return new ListBranchDTO(
            branch.getIdBranch(),
            branch.getNameBranch(),
            branch.getIdMunicipality(),
            null

        );
    }
    public branch convertDtoToBranch(ListBranchDTO branchDTO){
        municipality municipio = new municipality(0, null, null, null);
        municipio.setIdMunicipality(branchDTO.getIdMunicipality());
        return new branch(
            0,
            branchDTO.getNameBranch(),
            municipio
        );
    }
}
