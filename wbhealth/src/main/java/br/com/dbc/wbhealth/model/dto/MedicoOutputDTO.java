package br.com.dbc.wbhealth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoOutputDTO extends MedicoInputDTO {
    @Positive
    private Integer idPessoa;
    @Positive
    private Integer idMedico;
}
