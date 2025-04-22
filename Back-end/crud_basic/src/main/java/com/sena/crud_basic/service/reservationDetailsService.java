package com.sena.crud_basic.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.ListReservationDetails;
import com.sena.crud_basic.interfaces.IReservationDetails;
import com.sena.crud_basic.model.employees;
import com.sena.crud_basic.model.reservation;
import com.sena.crud_basic.model.reservation_details;
import com.sena.crud_basic.model.service;

@Service
public class reservationDetailsService {
    @Autowired
    private IReservationDetails reservationDetailsRepository;

     public List<ListReservationDetails> getAllReservationDetail() {
        try {
            var ReservationDetailList = reservationDetailsRepository.findAll();
            return ReservationDetailList.stream()
                    .map(this::convertReservationDetailsToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las suscursales: " + e.getMessage(), e);
        }
    }

    public Optional<ListReservationDetails> getReservationDetailById(int id) {
        try {
            return reservationDetailsRepository.findById(id)
                    .map(this::convertReservationDetailsToDto);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el Empleado por ID: " + e.getMessage(), e);
        }
    }

    public ListReservationDetails saveReservationDetail(ListReservationDetails ReservationDetailDTO) {
        try {
            if (ReservationDetailDTO == null) {
                throw new IllegalArgumentException("eL Empleado no puede ser null.");
            }
            var newReservationDetail = ReservationDetailDTO;
            reservationDetailsRepository.save(convertDtoToReservationDetails(newReservationDetail));
            return newReservationDetail;

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el Empleado: " + e.getMessage(), e);
        }
    }

    public ListReservationDetails updateReservationDetail(int id, ListReservationDetails ReservationDetailDTO) {
        try {
            if (ReservationDetailDTO == null) {
                throw new IllegalArgumentException("eL Empleado no puede ser null.");
            }

            var optionalReservationDetail = getReservationDetailById(id);
            if (optionalReservationDetail.isPresent()) {
 // Asegura mantener el ID original
                var newReservationDetail = ReservationDetailDTO;
                newReservationDetail.setIdReservationDetails(id);
                reservationDetailsRepository.save(convertDtoToReservationDetails(newReservationDetail));
                return newReservationDetail;
            } else {
                throw new RuntimeException("Empleado con ID " + id + " no encontrada.");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el Empleado: " + e.getMessage(), e);
        }
    }

    public void deleteReservationDetail(int id) {
        try {
            var optionalReservationDetail = reservationDetailsRepository.findById(id);
            if (optionalReservationDetail.isPresent()) {
                reservationDetailsRepository.deleteById(id);
            } else {
                throw new RuntimeException("Empleado con ID " + id + " no encontrada para eliminar.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el Empleado: " + e.getMessage(), e);
        }
    }

    public ListReservationDetails convertReservationDetailsToDto(reservation_details reservationDetails){
        return new ListReservationDetails(
            reservationDetails.getIdReservationDetails(),
            reservationDetails.getReservation().getIdReservation(),
            reservationDetails.getService().getIdService(), null,
            reservationDetails.getIdEmployee().getIdEmployee(),
            reservationDetails.getReservationDate(),
            reservationDetails.getReservationTime(), 
            reservationDetails.getDiscount(),
            reservationDetails.getCreationDate()
            );
    }

    public reservation_details convertDtoToReservationDetails(ListReservationDetails reservationDetailsDto){
        reservation reserva = new reservation();
        reserva.setIdReservation(reservationDetailsDto.getIdReservation());
        service servicio = new service();
        servicio.setIdService(reservationDetailsDto.getIdService());
        employees empleado = new employees();
        empleado.setIdEmployee(reservationDetailsDto.getIdEmployee());
        return new reservation_details(
            0,
            reserva,
            servicio,
            empleado,
            reservationDetailsDto.getReservationDate(), 
            reservationDetailsDto.getReservationTime(), 
            reservationDetailsDto.getDiscount(), 
            LocalDateTime.now()
        );
    }
}
