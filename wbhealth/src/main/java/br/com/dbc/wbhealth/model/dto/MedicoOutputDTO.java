package br.com.dbc.wbhealth.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Id de pessoa do medico", example = "1", required = true)
    @Positive
    private Integer idPessoa;
    @Schema(description = "Id de medico", example = "1", required = true)
    @Positive
    private Integer idMedico;
}
