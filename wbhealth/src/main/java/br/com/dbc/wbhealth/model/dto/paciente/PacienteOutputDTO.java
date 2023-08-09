package br.com.dbc.wbhealth.model.dto.paciente;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PacienteOutputDTO {
    private Integer idPessoa;
    private Integer idPaciente;
    private Integer idHospital;
    private String nome;
    private String cep;
    private LocalDate dataNascimento;
    private String cpf;
    private Double salarioMensal;
    private String email;
}
