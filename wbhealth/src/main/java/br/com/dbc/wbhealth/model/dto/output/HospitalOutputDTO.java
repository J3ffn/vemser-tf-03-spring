package br.com.dbc.wbhealth.model.dto.output;

import br.com.dbc.wbhealth.model.dto.input.HospitalInputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalOutputDTO extends HospitalInputDTO {

    @Positive
    @NotNull
    private Integer idHospital;
}
