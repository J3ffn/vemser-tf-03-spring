package br.com.dbc.wbhealth.model.dto.atendimento;

import br.com.dbc.wbhealth.model.enumarator.TipoDeAtendimento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AtendimentoInputDTO {

    @Positive
    @NotNull
    private Integer idHospital;

    @Positive
    @NotNull
    private Integer idPaciente;

    @Positive
    @NotNull
    private Integer idMedico;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat
    private LocalDate dataAtendimento;

    @NotBlank
    @NotNull
    private String laudo;

    @NotNull
    @NotBlank
    private String tipoDeAtendimento;

    @Positive
    @Nullable
    private Double valorDoAtendimento;

}
