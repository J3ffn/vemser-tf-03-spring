package br.com.dbc.wbhealth.model.dto.output;

import br.com.dbc.wbhealth.model.dto.input.AtendimentoInputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AtendimentoOutputDTO extends AtendimentoInputDTO {

    @Positive
    private Integer idAtendimento;

}
