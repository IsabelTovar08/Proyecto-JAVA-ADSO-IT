package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.ListPaymentMethod;
import com.sena.crud_basic.interfaces.IPaymentMethod;
import com.sena.crud_basic.model.payment_method;

@Service
public class paymentMethodService {
    @Autowired
    private IPaymentMethod paymentRepository;


        public List<ListPaymentMethod> getAllPayMethod() {
        try {
            var PayMethodList = paymentRepository.findAll();
            return PayMethodList.stream()
                    .map(this::convertPaymnetMethodToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las métodos de pago: " + e.getMessage(), e);
        }
    }

    public Optional<ListPaymentMethod> getPayMethodById(int id) {
        try {
            return paymentRepository.findById(id)
                    .map(this::convertPaymnetMethodToDto);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el método de pago por ID: " + e.getMessage(), e);
        }
    }

    public ListPaymentMethod savePayMethod(ListPaymentMethod payMethodDto) {
        try {
            if (payMethodDto == null) {
                throw new IllegalArgumentException("El método de pago no puede ser null.");
            }
            var newPayMethod = convertDtoToPaymnetMethod(payMethodDto);
            paymentRepository.save(newPayMethod);
            return convertPaymnetMethodToDto(newPayMethod);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el método de pago: " + e.getMessage(), e);
        }
    }

    public ListPaymentMethod updatePayMethod(int id, ListPaymentMethod payMethodDto) {
        try {
            if (payMethodDto == null) {
                throw new IllegalArgumentException("El método de pago no puede ser null.");
            }

            var optionalPayMethod = getPayMethodById(id);
            if (optionalPayMethod.isPresent()) {
 // Asegura mantener el ID original
                var newPayMethod = convertDtoToPaymnetMethod(payMethodDto);
                newPayMethod.setIdPaymentMethod(id);
                paymentRepository.save(newPayMethod);
                return convertPaymnetMethodToDto(newPayMethod);
            } else {
                throw new RuntimeException("método de pago con ID " + id + " no encontrado.");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la método de pago: " + e.getMessage(), e);
        }
    }

     public void deletePayMethod(int id) {
        try {
            var optionalPayMethod = paymentRepository.findById(id);
            if (optionalPayMethod.isPresent()) {
                paymentRepository.deleteById(id);
            } else {
                throw new RuntimeException("Método de pago con ID " + id + " no encontrado para eliminar.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el Método de pago: " + e.getMessage(), e);
        }
    }

    // Filtrar posiciones por nombre
    public List<ListPaymentMethod> filterPayMethods(String namePayMethod) {
        try {
            var filteredList = paymentRepository.filterPaymentMethods(namePayMethod);
            return filteredList.stream()
                    .map(this::convertPaymnetMethodToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al filtrar las sucursakes: " + e.getMessage(), e);
        }
    }

    public ListPaymentMethod convertPaymnetMethodToDto(payment_method paymnetMethod){
        return new ListPaymentMethod(
            paymnetMethod.getIdPaymentMethod(),
            paymnetMethod.getNamePaymentMethod()
        );
    }
    public payment_method convertDtoToPaymnetMethod(ListPaymentMethod PaymnetMethodDTO){
        return new payment_method(
            0,
            PaymnetMethodDTO.getNamePaymentMethod(),
            null
        );
    }
}
