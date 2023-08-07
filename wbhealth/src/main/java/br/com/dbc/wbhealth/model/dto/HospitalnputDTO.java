package br.com.dbc.wbhealth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalnputDTO {

    @NotBlank
    @Size(max = 50)
    private String nome;
}
