package br.com.dbc.wbhealth.service;

import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
import br.com.dbc.wbhealth.model.entity.Paciente;
import br.com.dbc.wbhealth.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository){
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> findAll() throws BancoDeDadosException {
        return pacienteRepository.findAll();
    }

    public Paciente findById(Integer idPaciente) throws BancoDeDadosException {
        return pacienteRepository.findById(idPaciente); // pode retornar null
    }

    public Paciente save(Paciente paciente) throws BancoDeDadosException {
        /*String cpf = paciente.getCpf().replaceAll("[^0-9]", "");
        if (cpf.length() != 11) {
            throw new Exception("CPF Invalido!");
        }
        paciente.setCpf(cpf);

        String cep = paciente.getCep().replaceAll("[^0-9]", "");
        if (cep.length() != 8) {
            throw new Exception("CEP inválido! Deve conter exatamente 8 dígitos numéricos.");
        }
        paciente.setCep(cep);*/

        return pacienteRepository.save(paciente);
    }

    public Paciente update(Integer idPaciente, Paciente pacienteModificado) throws BancoDeDadosException {
        return pacienteRepository.update(idPaciente, pacienteModificado);
    }

    public void deleteById(Integer idPaciente) throws BancoDeDadosException {
        pacienteRepository.deleteById(idPaciente);
    }

    public boolean buscarCpf(Paciente paciente){
        return pacienteRepository.buscarCpf(paciente);
    }
}
