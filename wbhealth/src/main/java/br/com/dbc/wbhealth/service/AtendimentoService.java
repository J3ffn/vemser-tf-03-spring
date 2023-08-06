package br.com.dbc.wbhealth.service;

import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
import br.com.dbc.wbhealth.exceptions.EntityNotFound;
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

    public Atendimento save(Atendimento atendimento) throws BancoDeDadosException {
        atendimentoRepository.save(atendimento);
        return atendimento;
    }

    public List<Atendimento> findAll() throws BancoDeDadosException {
        return atendimentoRepository.findAll();
    }

    public Atendimento findById(Integer id) throws BancoDeDadosException {
        return atendimentoRepository.findById(id);
    }

    public List<Atendimento> bucarAtendimentoPeloIdUsuario(Integer idPaciente) throws BancoDeDadosException {
        return findAll()
                .stream()
                .filter(atendimento -> atendimento.getIdPaciente().equals(idPaciente))
                .toList();
    }

    public Atendimento update(Integer id, Atendimento atendimentoAtualizado) throws BancoDeDadosException {
        atendimentoAtualizado.setIdAtendimento(id);
        return atendimentoRepository.update(id, atendimentoAtualizado);
    }

    public void deletarPeloId(Integer id) throws EntityNotFound {
        try {
            atendimentoRepository.deleteById(id);
        } catch (BancoDeDadosException e) {
            throw new RuntimeException(e);
        }
    }

}
