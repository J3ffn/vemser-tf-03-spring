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
        return pacienteRepository.save(paciente);
    }

    public Paciente update(Integer idPaciente, Paciente pacienteModificado) throws BancoDeDadosException {
        return pacienteRepository.update(idPaciente, pacienteModificado);
    }

    public void deleteById(Integer idPaciente) throws BancoDeDadosException {
        pacienteRepository.deleteById(idPaciente);
    }

    /*public boolean buscarCpf(Paciente paciente) throws BancoDeDadosException {
        return pacienteRepository.buscarCpf(paciente);
    }*/
}
