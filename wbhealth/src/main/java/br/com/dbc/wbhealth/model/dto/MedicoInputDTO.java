package br.com.dbc.wbhealth.model.dto;

import br.com.dbc.wbhealth.model.entity.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoInputDTO {
    @NotBlank
    private String nome;
    @NotBlank
    @Size(min =8, max=8)
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
    @NotBlank
    @Size(min=13, max=13)
    private String crm;

}
