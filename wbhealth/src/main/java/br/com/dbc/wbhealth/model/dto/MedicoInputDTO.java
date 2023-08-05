package br.com.dbc.wbhealth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoInputDTO {
    @NotNull
    private String nome;
    @NotBlank
    private LocalDate dataDeNascimento;
    @NotNull
    @Size(min = 8, max = 8)
    private String cep;
    @CPF
    private String cpf;
    @NotNull
    private Integer idHospital;
    @NotEmpty
    @Size(min= 6, max = 6)
    private String crm;
}
