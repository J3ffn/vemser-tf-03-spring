package com.example.wbhealth.service;


import com.example.wbhealth.model.Medico;
import com.example.wbhealth.model.exceptions.BancoDeDadosException;
import com.example.wbhealth.repository.MedicoRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository=medicoRepository;
    }

    public boolean buscarCpf(Medico medico) {
        return medicoRepository.buscarCpf(medico);
    }

    public Medico buscarId(Integer id) throws BancoDeDadosException {
        return medicoRepository.buscarId(id);
    }
    public Medico inserir(Medico medico) {
        Medico novoMedico=new Medico();
        try {
            String cpf = medico.getCpf().replaceAll("[^0-9]", "");
            if (cpf.length() != 11) {
                throw new Exception("CPF Invalido!");
            }
            medico.setCpf(cpf);

            String cep = medico.getCep().replaceAll("[^0-9]", "");
            if (cep.length() != 8) {
                throw new Exception("CEP inválido! Deve conter exatamente 8 dígitos numéricos.");
            }
            medico.setCep(cep);
            medicoRepository.cadastrar(medico);
            novoMedico= medico;

//            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unnexpected error: " + e.getMessage());
        }
        return novoMedico;
    }

    public ArrayList<Medico> listarTodos() throws BancoDeDadosException {
        return medicoRepository.listarTodos();
    }

    public void listarPeloId(Integer id) throws BancoDeDadosException {
        Medico medico = medicoRepository.listarPeloId(id);
        System.out.println(medico);
    }

    public Medico alterarPeloId(Integer id, Medico medicoAtualizado) throws BancoDeDadosException {
        Medico medico = new Medico();
        try {
            boolean consegueEditar = medicoRepository.alterarPeloId(id, medicoAtualizado);
            if (consegueEditar) {

                medico = medicoRepository.listarPeloId(id);
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return medico;
    }
    public String deletarPeloId(Integer id) {
        String retorno = new String();
        try {
            boolean removeu = medicoRepository.deletarPeloId(id);
            if (removeu) {
                retorno = "Medico deletado com sucesso.";
            }

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return retorno;

    }

}