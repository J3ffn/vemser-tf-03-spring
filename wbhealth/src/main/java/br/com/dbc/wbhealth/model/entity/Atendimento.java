package br.com.dbc.wbhealth.model.entity;

import br.com.dbc.wbhealth.model.enumarator.TipoDeAtendimento;
import org.springframework.lang.Nullable;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private LocalDate dataAtendimento;

    @NotBlank
    @NotNull
    private String laudo;

    @NotNull
    private TipoDeAtendimento tipoDeAtendimento;

    @Positive
    @Nullable
    private Double valorDoAtendimento;

    public Atendimento(Integer idHospital, Integer idPaciente, Integer idMedico, LocalDate dataAtendimento, String laudo, TipoDeAtendimento tipoDeAtendimento) {
        this.idHospital = idHospital;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.dataAtendimento = dataAtendimento;
        this.laudo = laudo;
        this.tipoDeAtendimento = tipoDeAtendimento;
        this.valorDoAtendimento = calcularValorDoAtendimento(tipoDeAtendimento);
    }

    public Double calcularValorDoAtendimento(TipoDeAtendimento tipoDeAtendimento) {
        return switch (tipoDeAtendimento.getCodigo()) {
            case 1 -> 200.0;
            case 2 -> 3000.0;
            case 3 -> 180.0;
            case 4 -> 100.0;
            case 5 -> 10.0;
            default -> 0.0;
        };
    }

    public Double getValorDoAtendimento() {
        return valorDoAtendimento;
    }

    public void setValorDoAtendimento(Double valorAtendimento) {
        valorDoAtendimento = valorAtendimento;
    }

    public TipoDeAtendimento getTipoDeAtendimento(){
        return tipoDeAtendimento;
    }

    public Integer getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(Integer idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public Integer getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(Integer idHospital) {
        this.idHospital = idHospital;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public LocalDate getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDate dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getLaudo() {
        return laudo;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    public void setTipoDeAtendimento(TipoDeAtendimento tipoDeAtendimento) {
        this.tipoDeAtendimento = tipoDeAtendimento;
    }

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
