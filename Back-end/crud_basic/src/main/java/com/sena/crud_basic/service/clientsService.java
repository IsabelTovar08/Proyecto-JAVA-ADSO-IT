package com.sena.crud_basic.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.ListClientsDTO;
import com.sena.crud_basic.DTO.requestDTO.requestClients;
import com.sena.crud_basic.interfaces.IClients;
import com.sena.crud_basic.model.clients;
import com.sena.crud_basic.model.person;

@Service
public class clientsService {

    @Autowired
    private IClients clientsRepository;



     public List<requestClients> getAllClients() {
        try {
            var ClientsList = clientsRepository.findAll();
            return ClientsList.stream()
                    .map(this::convertClientsTDtoListar)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las posiciones: " + e.getMessage(), e);
        }
    }

    public Optional<requestClients> getClientsById(int id) {
        try {
            return clientsRepository.findById(id)
                    .map(this::convertClientsTDtoListar);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la cliente por ID: " + e.getMessage(), e);
        }
    }

    public ListClientsDTO saveClients(ListClientsDTO clientsDTO) {
        try {
            if (clientsDTO == null) {
                throw new IllegalArgumentException("La cliente no puede ser null.");
            }
            var newClients = convertDtoToclients(clientsDTO);
            clientsRepository.save(newClients);
            return convertclientsToDto(newClients);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la cliente: " + e.getMessage(), e);
        }
    }

    public ListClientsDTO updateClients(int id, ListClientsDTO clientsDTO) {
        try {
            if (clientsDTO == null) {
                throw new IllegalArgumentException("La cliente no puede ser null.");
            }

            var optionalClients = getClientsById(id);
            if (optionalClients.isPresent()) {
 // Asegura mantener el ID original
                var newClients = convertDtoToclients(clientsDTO);
                newClients.setIdClient(id);
                clientsRepository.save(newClients);
                return convertclientsToDto(newClients);
            } else {
                throw new RuntimeException("cliente con ID " + id + " no encontrada.");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la cliente: " + e.getMessage(), e);
        }
    }

    public void deleteClients(int id) {
        try {
            var optionalClients = clientsRepository.findById(id);
            if (optionalClients.isPresent()) {
                clientsRepository.deleteById(id);
            } else {
                throw new RuntimeException("cliente con ID " + id + " no encontrada para eliminar.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la cliente: " + e.getMessage(), e);
        }
    }



    public ListClientsDTO convertclientsToDto(clients clients){
        return new ListClientsDTO(
            clients.getIdClient(),
            clients.getPerson().getIdPerson(),
            clients.getRegistrationDateClient()
        );
    }
    public clients convertDtoToclients(ListClientsDTO clientsDTO){
        person personn = new person();
        personn.setIdPerson(clientsDTO.getId_person());
        return new clients(
            0,
           personn,
           LocalDateTime.now()
        );
    }

    public requestClients convertClientsTDtoListar(clients clients){
        return new requestClients(
            clients.getIdClient(),
            clients.getPerson().getIdPerson(),
            clients.getPerson().getNamesPerson(),   
            clients.getRegistrationDateClient()

        );
    }
}
