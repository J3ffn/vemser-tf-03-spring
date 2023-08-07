package br.com.dbc.wbhealth.model.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Pessoa {

    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Positive
    private Integer idPessoa;
    @NotBlank
    private String nome;
    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;
    @NotNull
    @PastOrPresent
    private LocalDate dataNascimento;
    @CPF
    private String cpf;
    @PositiveOrZero
    private Double salarioMensal;

//    public Pessoa() {
//    }

    public Pessoa(String nome, String cep, String dataNascimento, String cpf, Double salarioMensal) {
        this.nome = nome;
        this.cep = cep;
        if (dataNascimento == ""){
            this.dataNascimento = null;
        }else {
            this.dataNascimento = LocalDate.parse(dataNascimento, fmt);
        }
        this.cpf = cpf;
        this.salarioMensal = salarioMensal;
    }

//    public Pessoa(String nome, String cep, String dataNascimento, String cpf, Double salarioMensal) {
//        this.nome = nome;
//        this.cep = cep;
//        if (dataNascimento == ""){
//            this.dataNascimento = null;
//        }else {
//            this.dataNascimento = LocalDate.parse(dataNascimento, fmt);
//        }
//        this.cpf = cpf;
//        this.salarioMensal = salarioMensal;
//    }

    // Getters & Setters
//    public Integer getIdPessoa() {
//        return idPessoa;
//    }
//
//    public void setIdPessoa(Integer idPessoa) {
//        this.idPessoa = idPessoa;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public String getCep() {
//        return cep;
//    }
//
//    public void setCep(String cep) {
//        this.cep = cep;
//    }
//
//    public LocalDate getDataNascimento() {
//        return dataNascimento;
//    }
//
//    public void setDataNascimento(LocalDate dataNascimento) {
//        this.dataNascimento = dataNascimento;
//    }
//
//    public String getCpf() {
//        return cpf;
//    }
//
//    public void setCpf(String cpf) {
//        this.cpf = cpf;
//    }
//
//    public Double getSalarioMensal() {
//        return salarioMensal;
//    }
//
//    public void setSalarioMensal(Double salarioMensal) {
//        this.salarioMensal = salarioMensal;
//    }
}
