package br.com.dbc.wbhealth.service;

import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
import br.com.dbc.wbhealth.model.dto.MedicoOutputDTO;
import br.com.dbc.wbhealth.model.entity.Medico;
import br.com.dbc.wbhealth.repository.MedicoRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository=medicoRepository;
    }

    public boolean buscarCpf(Medico medico) {
        return medicoRepository.buscarCpf(medico);
    }

//    public Medico buscarId(Integer id) throws BancoDeDadosException {
//        return medicoRepository.findById(id);

    public Medico save(Medico medico) {
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
            medicoRepository.save(medico);
            novoMedico= medico;

//            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unnexpected error: " + e.getMessage());
        }
        return novoMedico;
    }

    public ArrayList<Medico> findAll() throws BancoDeDadosException {
        ArrayList<Medico> listaMedico= medicoRepository.findAll();
        return listaMedico;
    }

    public Medico findById(Integer id) throws BancoDeDadosException {
        Medico medico = medicoRepository.findById(id);
        return medico;
    }

    public Medico update(Integer idMedico, Medico medicoAtualizado) throws BancoDeDadosException {
        Medico medico = new Medico();
        try {
            Medico medicoAux= medicoRepository.findAll().stream()
                    .filter(x -> x.getIdMedico() == idMedico)
                    .findFirst().orElseThrow(() -> new BancoDeDadosException (new Throwable("Id não encontrado")));
            medico = medicoRepository.findById(idMedico);

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return medico;
    }
    public String deletarPeloId(Integer id) {
        String retorno = new String();
        try {
            boolean removeu = medicoRepository.deleteById(id);
            if (removeu) {
                retorno = "Medico deletado com sucesso.";
            }

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return retorno;

    }

}
