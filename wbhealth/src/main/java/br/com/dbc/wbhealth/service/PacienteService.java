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

    public List<Paciente> listarTodos() throws BancoDeDadosException {
        return pacienteRepository.listarTodos();
    }

    public Paciente listarPeloId(Integer idPaciente) throws BancoDeDadosException {
        return pacienteRepository.listarPeloId(idPaciente); // pode retornar null
    }

    public Paciente inserir(Paciente paciente) {
        Paciente novoPaciente = null;
        try {
            String cpf = paciente.getCpf().replaceAll("[^0-9]", "");
            if (cpf.length() != 11) {
                throw new Exception("CPF Invalido!");
            }
            paciente.setCpf(cpf);

            String cep = paciente.getCep().replaceAll("[^0-9]", "");
            if (cep.length() != 8) {
                throw new Exception("CEP inválido! Deve conter exatamente 8 dígitos numéricos.");
            }
            paciente.setCep(cep);
            pacienteRepository.cadastrar(paciente);
            novoPaciente = paciente;
//            System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);

        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("Unnexpected error: " +  e.getMessage());
        }

        return paciente;
    }

    public boolean buscarCpf(Paciente paciente){
        return pacienteRepository.buscarCpf(paciente);
    }

    public Paciente alterarPeloId(Integer id, Paciente pacienteAtualizado) throws BancoDeDadosException {
        try {
            boolean consegueEditar = pacienteRepository.alterarPeloId(id, pacienteAtualizado);
            if (consegueEditar){
                return pacienteAtualizado;
            }
        }catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deletarPeloId(Integer id){
        try {
           boolean removeu =  pacienteRepository.deletarPeloId(id);
           if (removeu){
//               System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
           }

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }

    }

    public Paciente buscarId(Integer id) throws BancoDeDadosException {
        return pacienteRepository.buscarId(id);
    }
}
