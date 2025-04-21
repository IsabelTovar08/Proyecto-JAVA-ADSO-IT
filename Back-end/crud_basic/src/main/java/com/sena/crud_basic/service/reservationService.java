package com.sena.crud_basic.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.ListReservationDTO;
import com.sena.crud_basic.interfaces.IReservation;
import com.sena.crud_basic.model.clients;
import com.sena.crud_basic.model.payment_method;
import com.sena.crud_basic.model.reservation;

@Service
public class reservationService {
    @Autowired
    private IReservation reservationRepository;

    public List<ListReservationDTO> getAllReservation() {
        try {
            var ReservationList = reservationRepository.findAll();
            return ReservationList.stream()
                    .map(this::convertReservationToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las reservas: " + e.getMessage(), e);
        }
    }

    public Optional<ListReservationDTO> getReservationById(int id) {
        try {
            return reservationRepository.findById(id)
                    .map(this::convertReservationToDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la Reserva por ID: " + e.getMessage(), e);
        }
    }

    public ListReservationDTO saveReservation(ListReservationDTO ReservationDTO) {
        try {
            if (ReservationDTO == null) {
                throw new IllegalArgumentException("La Reserva no puede ser null.");
            }
            var newReservation = convertDtoToReservation(ReservationDTO);
            reservationRepository.save(newReservation);
            return convertReservationToDTO(newReservation);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la Reserva: " + e.getMessage(), e);
        }
    }

    public ListReservationDTO updateReservation(int id, ListReservationDTO ReservationDTO) {
        try {
            if (ReservationDTO == null) {
                throw new IllegalArgumentException("La Reserva no puede ser null.");
            }

            var optionalReservation = getReservationById(id);
            if (optionalReservation.isPresent()) {
                // Asegura mantener el ID original
                var newReservation = convertDtoToReservation(ReservationDTO);
                newReservation.setIdReservation(id);
                reservationRepository.save(newReservation);
                return convertReservationToDTO(newReservation);
            } else {
                throw new RuntimeException("Reserva con ID " + id + " no encontrada.");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la Reserva: " + e.getMessage(), e);
        }
    }

    public void deleteReservation(int id) {
        try {
            var optionalReservation = reservationRepository.findById(id);
            if (optionalReservation.isPresent()) {
                reservationRepository.deleteById(id);
            } else {
                throw new RuntimeException("Reserva con ID " + id + " no encontrada para eliminar.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la Reserva: " + e.getMessage(), e);
        }
    }

    // // Filtrar posiciones por nombre
    // public List<ListReservationDTO> filterReservations(String nameReservation) {
    //     try {
    //         var filteredList = reservationRepository.filterReservations(nameReservation);
    //         return filteredList.stream()
    //                 .map(this::convertReservationToDTO)
    //                 .collect(Collectors.toList());
    //     } catch (Exception e) {
    //         throw new RuntimeException("Error al filtrar las sucursakes: " + e.getMessage(), e);
    //     }
    // }

    public ListReservationDTO convertReservationToDTO(reservation reservation) {
        return new ListReservationDTO(
                0,
                reservation.getClients().getIdClient(),
                reservation.getPaymentMethod().getIdPaymentMethod(),
                LocalDateTime.now());
    }

    public reservation convertDtoToReservation(ListReservationDTO reservationDto) {
        clients cliente = new clients(reservationDto.getIdClient(), null, null);
        payment_method metodo_pago = new payment_method(reservationDto.getIdPaymentMethod(), null, null);
        return new reservation(
                reservationDto.getIdReservation(),
                cliente,
                metodo_pago,
                reservationDto.getCreationDate(),
                null);
    }
}
