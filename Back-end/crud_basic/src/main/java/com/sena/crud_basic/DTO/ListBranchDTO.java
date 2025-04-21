package com.sena.crud_basic.DTO;

public class ListBranchDTO {
    private int idBranch;
    private String nameBranch;
    private int idMunicipality;
    public ListBranchDTO(int idBranch, String nameBranch, int idMunicipality) {
        this.idBranch = idBranch;
        this.nameBranch = nameBranch;
        this.idMunicipality = idMunicipality;
    }
    public int getIdBranch() {
        return idBranch;
    }
    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
    }
    public String getNameBranch() {
        return nameBranch;
    }
    public void setNameBranch(String nameBranch) {
        this.nameBranch = nameBranch;
    }
    public int getIdMunicipality() {
        return idMunicipality;
    }
    public void setIdMunicipality(int idMunicipality) {
        this.idMunicipality = idMunicipality;
    }
   
}
