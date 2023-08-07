package br.com.dbc.wbhealth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalOutputDTO extends HospitalnputDTO{

    @Positive
    private Integer idHospital;
}
