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

    public Atendimento save(Atendimento atendimento) throws BancoDeDadosException {
        atendimentoRepository.save(atendimento);
        return atendimento;
    }

    public List<Atendimento> findAll() throws BancoDeDadosException {
        return atendimentoRepository.findAll();
    }

    public Atendimento getAtendimentoPeloId(Integer id) throws BancoDeDadosException {
        return atendimentoRepository.findById(id);
    }

    public List<Atendimento> findByIdPaciente(Integer idPaciente) throws BancoDeDadosException {
        return findByIdPaciente(idPaciente)
                .stream()
                .filter(atendimento -> atendimento.getIdPaciente().equals(idPaciente))
                .toList();
    }

    public Atendimento update(Integer id, Atendimento atendimentoAtualizado) throws BancoDeDadosException {
        atendimentoAtualizado.setIdAtendimento(id);
        return atendimentoRepository.update(id, atendimentoAtualizado);
    }

    public void deleteById(Integer id){
        try {
            boolean removeu =  atendimentoRepository.deleteById(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public Atendimento buscarId(Integer id) throws BancoDeDadosException {
        return atendimentoRepository.findById(id);
    }

}
