package com.sena.crud_basic.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.ListEmployees;
import com.sena.crud_basic.DTO.ListEmployees;
import com.sena.crud_basic.DTO.requestDTO.requestEmployees;
import com.sena.crud_basic.DTO.ListEmployees;
import com.sena.crud_basic.interfaces.IEmployess;
import com.sena.crud_basic.model.employees;
import com.sena.crud_basic.model.employees;
import com.sena.crud_basic.model.municipality;
import com.sena.crud_basic.model.person;
import com.sena.crud_basic.model.position;

@Service
public class employeesService {
 @Autowired
 private IEmployess employeesRepository;


public List<requestEmployees> getAllEmployees() {
        try {
            var Employees = employeesRepository.findAll();
            return Employees.stream()
                    .map(this::convertemployeesToDtoListar)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las Employees: " + e.getMessage(), e);
        }
    }

    public Optional<requestEmployees> getEmployeesById(int id) {
        try {
            return employeesRepository.findById(id)
                    .map(this::convertemployeesToDtoListar);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la Employees por ID: " + e.getMessage(), e);
        }
    }


    public ListEmployees saveEmployees(ListEmployees EmployeesDTO) {
        try {
            if (EmployeesDTO == null) {
                throw new IllegalArgumentException("La Sucursal no puede ser null.");
            }
            var newEmployees = convertDtoToemployees(EmployeesDTO);
            employeesRepository.save(newEmployees);
            return convertemployeesToDto(newEmployees);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la sucursal: " + e.getMessage(), e);
        }
    }

    public ListEmployees updateEmployees(int id, ListEmployees EmployeesDTO) {
        try {
            if (EmployeesDTO == null) {
                throw new IllegalArgumentException("La sucursal no puede ser null.");
            }

            var optionalEmployees = getEmployeesById(id);
            if (optionalEmployees.isPresent()) {
 // Asegura mantener el ID original
                var newEmployees = convertDtoToemployees(EmployeesDTO);
                newEmployees.setIdEmployee(id);
                employeesRepository.save(newEmployees);
                return convertemployeesToDto(newEmployees);
            } else {
                throw new RuntimeException("Sucursal con ID " + id + " no encontrada.");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la Sucursal: " + e.getMessage(), e);
        }
    }

    public void deleteEmployees(int id) {
        try {
            var optionalEmployees = employeesRepository.findById(id);
            if (optionalEmployees.isPresent()) {
                employeesRepository.deleteById(id);
            } else {
                throw new RuntimeException("Sucursal con ID " + id + " no encontrada para eliminar.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la Sucursal: " + e.getMessage(), e);
        }
    }



   public ListEmployees convertemployeesToDto(employees employees){
        return new ListEmployees(
          employees.getIdEmployee(),
          employees.getPerson().getIdPerson(),
          employees.getPosition().getIdPosition(),
          employees.getSalaryEmployee(),
          employees.getCreationDate()
  
        );
    }
    public employees convertDtoToemployees(ListEmployees employeesDTO){
        person person = new person();
        person.setIdPerson(employeesDTO.getId_person());

        position position = new position();
        position.setIdPosition(employeesDTO.getId_position());
        return new employees(
            0,
            person,
            position,
            employeesDTO.getSalaryEmployee(),
            LocalDateTime.now(),
            null
        );
    }

    
   public requestEmployees convertemployeesToDtoListar(employees employees){
    return new requestEmployees(
      employees.getIdEmployee(),
      employees.getPerson().getIdPerson(),
      employees.getPerson().getNamesPerson(),
      employees.getPosition().getIdPosition(),
      employees.getPosition().getNamePosition(),
      employees.getSalaryEmployee(),
      employees.getCreationDate()

    );
}


}
