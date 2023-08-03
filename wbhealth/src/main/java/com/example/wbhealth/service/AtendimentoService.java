package com.example.wbhealth.service;


import com.example.wbhealth.model.Atendimento;
import com.example.wbhealth.model.exceptions.BancoDeDadosException;
import com.example.wbhealth.repository.AtendimentoRepository;

import java.util.List;

public class AtendimentoService {
    private final AtendimentoRepository atendimentoRepository;

    public AtendimentoService(AtendimentoRepository atendimentoRepository) {
        this.atendimentoRepository = atendimentoRepository;
    }

    public void inserir(Atendimento atendimento) throws BancoDeDadosException {
        atendimentoRepository.cadastrar(atendimento);
    }

    public void listarTodos() throws BancoDeDadosException {
        try {
            List<Atendimento> list = atendimentoRepository.listarTodos();
            list.forEach(System.out::println);
        }catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }

    public void listarPeloId(Integer id) throws BancoDeDadosException {
        Atendimento atendimento = atendimentoRepository.listarPeloId(id);
        System.out.println(atendimento);
    }

    public void alterarPeloId(Integer id, Atendimento atendimentoAtualizado){
        try {
            boolean consegueEditar = atendimentoRepository.alterarPeloId(id, atendimentoAtualizado);
            if (consegueEditar){
//                System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
            }
        }catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void deletarPeloId(Integer id){
        try {
            boolean removeu =  atendimentoRepository.deletarPeloId(id);
            if (removeu){
//                System.out.println(CoresMenu.VERDE_BOLD + "\nOperação realizada com sucesso!" + CoresMenu.RESET);
            }

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
