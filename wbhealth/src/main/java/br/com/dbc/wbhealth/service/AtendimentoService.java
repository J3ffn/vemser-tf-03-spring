package br.com.dbc.wbhealth.service;

import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
import br.com.dbc.wbhealth.exceptions.EntityNotFound;
import br.com.dbc.wbhealth.model.dto.atendimento.AtendimentoInputDTO;
import br.com.dbc.wbhealth.model.dto.atendimento.AtendimentoOutputDTO;
import br.com.dbc.wbhealth.model.entity.Atendimento;
import br.com.dbc.wbhealth.repository.AtendimentoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtendimentoService {
    private final AtendimentoRepository atendimentoRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public AtendimentoService(AtendimentoRepository atendimentoRepository, ObjectMapper objectMapper) {
        this.atendimentoRepository = atendimentoRepository;
        this.objectMapper = objectMapper;
    }

    public AtendimentoOutputDTO save(AtendimentoInputDTO atendimentoNovo) throws BancoDeDadosException {
        Atendimento atendimento = objectMapper.convertValue(atendimentoNovo, Atendimento.class);
        atendimento = atendimentoRepository.save(atendimento);
        return objectMapper.convertValue(atendimento, AtendimentoOutputDTO.class);
    }

    public List<AtendimentoOutputDTO> findAll() throws BancoDeDadosException {
        return atendimentoRepository.findAll()
                .stream()
                .map(atendimento -> objectMapper.convertValue(atendimento, AtendimentoOutputDTO.class))
                .toList();
    }

    public AtendimentoOutputDTO findById(Integer id) throws BancoDeDadosException, EntityNotFound {
        return objectMapper.convertValue(atendimentoRepository.findById(id), AtendimentoOutputDTO.class);
    }

    public List<AtendimentoOutputDTO> bucarAtendimentoPeloIdUsuario(Integer idPaciente) throws BancoDeDadosException {
        return findAll()
                .stream()
                .filter(atendimento -> atendimento.getIdPaciente().equals(idPaciente))
                .toList();
    }

    public AtendimentoOutputDTO update(Integer id, AtendimentoInputDTO atendimentoAtualizado) throws BancoDeDadosException, EntityNotFound {

        Atendimento atendimentoConvertido = objectMapper.convertValue(atendimentoAtualizado, Atendimento.class);
        Atendimento atendimentoModificado = atendimentoRepository.update(id, atendimentoConvertido);
        return objectMapper.convertValue(atendimentoModificado, AtendimentoOutputDTO.class);
    }

    public void deletarPeloId(Integer id) throws EntityNotFound {
        try {
            atendimentoRepository.deleteById(id);
        } catch (BancoDeDadosException e) {
            throw new RuntimeException(e);
        }
    }

}
