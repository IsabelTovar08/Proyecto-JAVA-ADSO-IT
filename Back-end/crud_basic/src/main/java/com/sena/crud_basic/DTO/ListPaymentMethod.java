package com.sena.crud_basic.DTO;

public class ListPaymentMethod {
    private int idPaymentMethod;
    private String namePaymentMethod;
    public ListPaymentMethod(int idPaymentMethod, String namePaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
        this.namePaymentMethod = namePaymentMethod;
    }
    public int getIdPaymentMethod() {
        return idPaymentMethod;
    }
    public void setIdPaymentMethod(int idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }
    public String getNamePaymentMethod() {
        return namePaymentMethod;
    }
    public void setNamePaymentMethod(String namePaymentMethod) {
        this.namePaymentMethod = namePaymentMethod;
    }
}
