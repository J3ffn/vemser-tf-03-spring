package br.com.dbc.wbhealth.model.entity;

import br.com.dbc.wbhealth.model.enumarator.TipoDeAtendimento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Atendimento {

    @Positive
    private Integer idAtendimento;

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
    private TipoDeAtendimento tipoDeAtendimento;

    @Positive
    @Nullable
    private Double valorDoAtendimento;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(this.idAtendimento);
        sb.append("\nData: ").append(this.dataAtendimento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        sb.append("\nLaudo: ").append(this.laudo);
        sb.append("\nTipo de Atendimento: ").append(this.tipoDeAtendimento);
        sb.append("\nValor: R$").append(String.format("%.2f", this.valorDoAtendimento));
        return sb.toString();
    }
}
