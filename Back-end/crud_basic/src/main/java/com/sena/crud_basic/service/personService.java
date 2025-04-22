package com.sena.crud_basic.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.ListPersonDTO;
import com.sena.crud_basic.DTO.requestDTO.requestPerson;
import com.sena.crud_basic.interfaces.IPerson;
import com.sena.crud_basic.model.municipality;
import com.sena.crud_basic.model.person;

@Service
public class personService {
    @Autowired
    private IPerson personRepository;

    public List<requestPerson> getAllPerson() {
        try {
            var branchList = personRepository.findAll();
            return branchList.stream()
                    .map(this::convertPersonchDtoList)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las posiciones: " + e.getMessage(), e);
        }
    }

    public Optional<requestPerson> getPersonById(int id) {
        try {
            return personRepository.findById(id)
                    .map(this::convertPersonchDtoList);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la persona por ID: " + e.getMessage(), e);
        }
    }

    public ListPersonDTO savePerson(ListPersonDTO perosnDTO) {
        try {
            if (perosnDTO == null) {
                throw new IllegalArgumentException("La persona no puede ser null.");
            }
            var newPerson = convertDtoPerson(perosnDTO);
            personRepository.save(newPerson);
            return convertPersonchDto(newPerson);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la persona: " + e.getMessage(), e);
        }
    }

    public ListPersonDTO updatePerson(int id, ListPersonDTO perosnDTo) {
        try {
            if (perosnDTo == null) {
                throw new IllegalArgumentException("La persona no puede ser null.");
            }

            var optionalPerson = getPersonById(id);
            if (optionalPerson.isPresent()) {
                // Asegura mantener el ID original
                var newBranch = convertDtoPerson(perosnDTo);
                newBranch.setIdPerson(id);
                personRepository.save(newBranch);
                return convertPersonchDto(newBranch);
            } else {
                throw new RuntimeException("persona con ID " + id + " no encontrada.");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la persona: " + e.getMessage(), e);
        }
    }

    public void deletePerson(int id) {
        try {
            var optionalPerson = personRepository.findById(id);
            if (optionalPerson.isPresent()) {
                personRepository.deleteById(id);
            } else {
                throw new RuntimeException("persona con ID " + id + " no encontrada para eliminar.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la persona: " + e.getMessage(), e);
        }
    }

    public ListPersonDTO convertPersonchDto(person person) {
        return new ListPersonDTO(
                person.getIdPerson(),
                person.getNamesPerson(),
                person.getLastNamePerson(),
                person.getDocumentPerson(),
                person.getPhonePerson(),
                person.getEmailPerson(),
                person.getAddressPerson(),
                person.getMunicipality().getIdMunicipality(),
                person.getCreationDate());
    }

    public person convertDtoPerson(ListPersonDTO listPersponDTO) {
        municipality municipio = new municipality(0, null, null, null);
        municipio.setIdMunicipality(listPersponDTO.getIdMunicipality());

        return new person(
                0,
                listPersponDTO.getNamesPerson(),
                listPersponDTO.getLastNamePerson(),
                listPersponDTO.getDocumentPerson(),
                listPersponDTO.getPhonePerson(),
                listPersponDTO.getEmailPerson(),
                listPersponDTO.getAddressPerson(),
                municipio,
                LocalDateTime.now(),
                null,
                null

        );

    }

    public requestPerson convertPersonchDtoList(person person) {
        return new requestPerson(
                person.getIdPerson(),
                person.getNamesPerson(),
                person.getLastNamePerson(),
                person.getDocumentPerson(),
                person.getPhonePerson(),
                person.getEmailPerson(),
                person.getAddressPerson(),
                person.getMunicipality().getIdMunicipality(),
                person.getMunicipality().getNameMunicipality(),
                person.getCreationDate());
    }

}
