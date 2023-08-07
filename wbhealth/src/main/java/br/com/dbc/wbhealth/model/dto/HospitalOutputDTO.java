package br.com.dbc.wbhealth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
