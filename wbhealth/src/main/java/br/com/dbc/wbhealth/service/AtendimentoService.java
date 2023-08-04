package br.com.dbc.wbhealth.service;

import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
import br.com.dbc.wbhealth.model.entity.Atendimento;
import br.com.dbc.wbhealth.repository.AtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtendimentoService {
    private final AtendimentoRepository atendimentoRepository;

    @Autowired
    public AtendimentoService(AtendimentoRepository atendimentoRepository) {
        this.atendimentoRepository = atendimentoRepository;
    }

    public Atendimento inserir(Atendimento atendimento) throws BancoDeDadosException {
        atendimentoRepository.cadastrar(atendimento);
        return atendimento;
    }

    public List<Atendimento> listarTodos() throws BancoDeDadosException {
        return atendimentoRepository.listarTodos();
    }

    public Atendimento getAtendimentoPeloId(Integer id) throws BancoDeDadosException {
        return atendimentoRepository.listarPeloId(id);
    }

    public List<Atendimento> getAtendimentoPeloIdUsuario(Integer idPaciente) throws BancoDeDadosException {
        return listarTodos()
                .stream()
                .filter(atendimento -> atendimento.getIdPaciente().equals(idPaciente))
                .toList();
    }

    public Atendimento alterarPeloId(Integer id, Atendimento atendimentoAtualizado) throws BancoDeDadosException {
        atendimentoAtualizado.setIdAtendimento(id);
        return atendimentoRepository.alterarPeloId(id, atendimentoAtualizado) ? atendimentoAtualizado : null;
    }

    public void deletarPeloId(Integer id){
        try {
            boolean removeu =  atendimentoRepository.deletarPeloId(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public Atendimento buscarId(Integer id) throws BancoDeDadosException {
        return atendimentoRepository.buscarId(id);
    }

    public List<Atendimento> buscarTodos() throws BancoDeDadosException{
        return atendimentoRepository.buscarTodos();
    }
}
