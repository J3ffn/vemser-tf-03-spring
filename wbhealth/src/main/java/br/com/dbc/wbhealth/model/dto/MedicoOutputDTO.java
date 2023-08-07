package br.com.dbc.wbhealth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoOutputDTO extends MedicoInputDTO {
    @NotNull
    private Integer idMedico;
}
