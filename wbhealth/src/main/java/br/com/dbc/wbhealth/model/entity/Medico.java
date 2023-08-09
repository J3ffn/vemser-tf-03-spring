package br.com.dbc.wbhealth.model.entity;

import br.com.dbc.wbhealth.model.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Medico extends Pessoa implements Pagamento {
    @Positive
    private Integer idPessoa;
    @Positive
    private Integer idHospital;
    @Positive
    private Integer idMedico;
    @NotBlank
    @Size(min=13, max=13)
    private String crm;


    public Medico(String nome, String cep, String dataNascimento, String cpf, Double salarioMensal, Integer idHospital, String crm, String email) {
        super(nome, cep, dataNascimento, cpf, salarioMensal, email);
        this.idHospital = idHospital;
        this.crm = crm;
    }

    // Getters & Setters
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(this.getIdMedico());
        sb.append("\nMédico: ").append(this.getNome());
        sb.append("\nCRM: ").append(this.getCrm());
        sb.append("\nSalário Mensal: R$").append(String.format("%.2f", this.getSalarioMensal()));
        return sb.toString();
    }

    @Override
    public Double calcularSalarioMensal() {
        Double taxaInss = 0.14;
        return getSalarioMensal() - getSalarioMensal() * taxaInss;
    }

    @Override
    public Integer getIdPessoa() {
        return idPessoa;
    }

    @Override
    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }
}
