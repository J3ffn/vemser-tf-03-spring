package br.com.dbc.wbhealth.repository;


import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repositorio<Key, T> {
    public T save(T entidade) throws BancoDeDadosException;

    public List<T> findALL() throws BancoDeDadosException;

    public T findById(Key id) throws BancoDeDadosException;

    public T update(Key id, T entidadeAtualizada) throws BancoDeDadosException;

    public boolean deleteById(Key id) throws BancoDeDadosException;

    public Integer getProximoId(Connection connection, String nextSequence) throws SQLException;
//    public T buscarId(Key id) throws BancoDeDadosException;

}
