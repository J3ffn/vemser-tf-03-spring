package br.com.dbc.wbhealth.service;

import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
import br.com.dbc.wbhealth.exceptions.EntityNotFound;
import br.com.dbc.wbhealth.exceptions.NegocioException;
import br.com.dbc.wbhealth.model.dto.paciente.PacienteInputDTO;
import br.com.dbc.wbhealth.model.dto.paciente.PacienteOutputDTO;
import br.com.dbc.wbhealth.model.entity.Paciente;
import br.com.dbc.wbhealth.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;
    private final ObjectMapper objectMapper;

    public PacienteService(PacienteRepository pacienteRepository, ObjectMapper objectMapper){
        this.pacienteRepository = pacienteRepository;
        this.objectMapper = objectMapper;
    }

    public List<PacienteOutputDTO> findAll() throws BancoDeDadosException {
        return pacienteRepository.findAll()
                .stream().map(this::convertPacienteToOutput).toList();
    }

    public PacienteOutputDTO findById(Integer idPaciente) throws BancoDeDadosException, EntityNotFound {
        Paciente pacienteEncontrado = pacienteRepository.findById(idPaciente);
        return convertPacienteToOutput(pacienteEncontrado);
    }

    public PacienteOutputDTO save(PacienteInputDTO pacienteInput) throws BancoDeDadosException {
        Paciente paciente = convertInputToPaciente(pacienteInput);
        Paciente novoPaciente = pacienteRepository.save(paciente);
        return convertPacienteToOutput(novoPaciente);
    }

    public PacienteOutputDTO update(Integer idPaciente, PacienteInputDTO pacienteInput)
            throws BancoDeDadosException, EntityNotFound {
        Paciente pacienteModificado = convertInputToPaciente(pacienteInput);
        Paciente pacienteAtualizado = pacienteRepository.update(idPaciente, pacienteModificado);
        return convertPacienteToOutput(pacienteAtualizado);
    }

    public void deleteById(Integer idPaciente) throws BancoDeDadosException, EntityNotFound {
        pacienteRepository.deleteById(idPaciente);
    }

    /*public boolean buscarCpf(Paciente paciente) throws BancoDeDadosException {
        return pacienteRepository.buscarCpf(paciente);
    }*/

    private Paciente convertInputToPaciente(PacienteInputDTO pacienteInput){
        Paciente paciente = objectMapper.convertValue(pacienteInput, Paciente.class);
        paciente.setNome(pacienteInput.getNome());
        paciente.setCep(pacienteInput.getCep());
        paciente.setDataNascimento(pacienteInput.getDataNascimento());
        paciente.setCpf(pacienteInput.getCpf());
        paciente.setSalarioMensal(pacienteInput.getSalarioMensal());
        paciente.setIdHospital(pacienteInput.getIdHospital());
        return paciente;
    }

    private PacienteOutputDTO convertPacienteToOutput(Paciente paciente){
        return new PacienteOutputDTO(
                paciente.getIdPessoa(),
                paciente.getIdPaciente(),
                paciente.getIdHospital(),
                paciente.getNome(),
                paciente.getCep(),
                paciente.getDataNascimento(),
                paciente.getCpf(),
                paciente.getSalarioMensal()
        );
    }
}
