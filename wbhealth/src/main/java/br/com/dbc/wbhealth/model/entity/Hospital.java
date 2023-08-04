package br.com.dbc.wbhealth.model.entity;

import javax.validation.constraints.*;

public class Hospital {

    private Integer idHospital;
    @NotBlank
    @Size(max = 50)
    private String nome;

    public Hospital() {
    }
    public Hospital(Integer id, String nome) {
        this.idHospital = id;
        this.nome = nome;
    }

    public Integer getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(Integer idHospital) {
        this.idHospital = idHospital;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "idHospital=" + idHospital +
                ", nome='" + nome + '\'' +
                '}';
    }
}
