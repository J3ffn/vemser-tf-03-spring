package br.com.dbc.wbhealth.model.entity;

import javax.validation.constraints.Positive;
import java.time.format.DateTimeFormatter;

public class Paciente extends Pessoa {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    @Positive
    private Integer idPaciente;
    @Positive
    private Integer idHospital;

    public Paciente() {
    }

    public Paciente(String nome, String cep, String dataNacimento,
                    String cpf, Double salarioMensal, Integer idHospital) {
        super(nome, cep, dataNacimento, cpf, salarioMensal);
        this.idHospital = idHospital;
    }

    // Getters & Setters
    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(Integer idHospital) {
        this.idHospital = idHospital;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(this.getIdPaciente());
        sb.append("\nPaciente: ").append(this.getNome());
        sb.append("\nCPF: ").append(this.getCpf());
        sb.append("\nData Nascimento: ").append(this.getDataNascimento().format(FORMAT));
        return sb.toString();
    }

}
