package br.com.dbc.wbhealth.model.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


public class MedicoInputDTO {
    @NotNull
    private String nome;
    @NotBlank
    @DateTimeFormat(pattern= "" )
    private LocalDate dataDeNascimento;
    @NotNull
    private String cep;
    private String cpf;
    private Integer idHospital;
    private String crm;
}
