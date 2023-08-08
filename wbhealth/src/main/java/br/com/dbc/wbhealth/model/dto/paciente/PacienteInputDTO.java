package br.com.dbc.wbhealth.model.dto.paciente;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class PacienteInputDTO {
    @NotBlank
    private String nome;
    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;
    @NotNull
    @PastOrPresent
    private LocalDate dataNascimento;
    @CPF
    private String cpf;
    @PositiveOrZero
    private Double salarioMensal;
    @Positive
    private Integer idHospital;
}
