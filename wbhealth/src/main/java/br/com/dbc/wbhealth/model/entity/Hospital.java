package br.com.dbc.wbhealth.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {

    @Positive
    @NotNull
    private Integer idHospital;

    @NotBlank
    @Size(max = 50)
    private String nome;
}
