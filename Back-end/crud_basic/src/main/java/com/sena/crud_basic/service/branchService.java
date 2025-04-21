package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.ListBranchDTO;
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
            throw new RuntimeException("Error al obtener la sucursal por ID: " + e.getMessage(), e);
        }
    }

    public ListBranchDTO saveBranch(ListBranchDTO BranchDTO) {
        try {
            if (BranchDTO == null) {
                throw new IllegalArgumentException("La Sucursal no puede ser null.");
            }
            var newBranch = convertDtoToBranch(BranchDTO);
            branchRepository.save(newBranch);
            return convertBranchToDto(newBranch);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la sucursal: " + e.getMessage(), e);
        }
    }

    public ListBranchDTO updateBranch(int id, ListBranchDTO BranchDTO) {
        try {
            if (BranchDTO == null) {
                throw new IllegalArgumentException("La sucursal no puede ser null.");
            }

            var optionalBranch = getBranchById(id);
            if (optionalBranch.isPresent()) {
 // Asegura mantener el ID original
                var newBranch = convertDtoToBranch(BranchDTO);
                newBranch.setIdBranch(id);
                branchRepository.save(newBranch);
                return convertBranchToDto(newBranch);
            } else {
                throw new RuntimeException("Sucursal con ID " + id + " no encontrada.");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la Sucursal: " + e.getMessage(), e);
        }
    }

    public void deleteBranch(int id) {
        try {
            var optionalBranch = branchRepository.findById(id);
            if (optionalBranch.isPresent()) {
                branchRepository.deleteById(id);
            } else {
                throw new RuntimeException("Sucursal con ID " + id + " no encontrada para eliminar.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la Sucursal: " + e.getMessage(), e);
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
            branch.getMunicipality().getIdMunicipality()
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
