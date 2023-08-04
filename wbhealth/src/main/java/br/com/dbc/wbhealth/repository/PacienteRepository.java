package br.com.dbc.wbhealth.repository;

import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
import br.com.dbc.wbhealth.model.entity.Paciente;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PacienteRepository implements Repositorio<Integer, Paciente> {
    @Override
    public List<Paciente> findAll() throws BancoDeDadosException {
        List<Paciente> listaPacientes = new ArrayList<>();
        Connection conexao = null;

        try {
            conexao = ConexaoBancoDeDados.getConnection();
            final String QUERY_SQL = "SELECT * FROM PESSOA\n"
                                    + "INNER JOIN PACIENTE\n"
                                    + "ON PESSOA.ID_PESSOA = PACIENTE.ID_PESSOA";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_SQL);

            while (resultSet.next()){
                Paciente paciente = obterPaciente(resultSet);
                listaPacientes.add(paciente);
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            fecharConexaoComBancoDeDados(conexao);
        }

        return listaPacientes;
    }

    @Override
    public Paciente findById(Integer idPaciente) throws BancoDeDadosException {
        Paciente paciente = null;
        Connection conexao = null;

        try {
            conexao = ConexaoBancoDeDados.getConnection();
            final String QUERY_SQL = "SELECT * FROM PACIENTE\n"
                                    + "INNER JOIN PESSOA ON PACIENTE.ID_PACIENTE = ? "
                                    + "AND PESSOA.ID_PESSOA = PACIENTE.ID_PESSOA\n";
            PreparedStatement statement = conexao.prepareStatement(QUERY_SQL);
            statement.executeQuery(QUERY_SQL);
            statement.setInt(1, idPaciente);
            ResultSet result = statement.executeQuery();

            if (result.next()){
                paciente = obterPaciente(result);
            }

        }catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            fecharConexaoComBancoDeDados(conexao);
        }
        return paciente;
    }

    @Override
    public Paciente save(Paciente paciente) throws BancoDeDadosException {
        Connection conexao = null;

        try {
            conexao = ConexaoBancoDeDados.getConnection();

            final String QUERY_PESSOA = "INSERT INTO PESSOA\n"
                    + "(id_pessoa, nome, cep, data_nascimento, cpf, salario_mensal)\n"
                    + "VALUES(?, ?, ?, ?, ?, ?)\n";
            inserirNaTabelaPessoa(paciente, conexao, QUERY_PESSOA);

            final String QUERY_PACIENTE = "INSERT INTO PACIENTE\n"
                                        + "(id_paciente, id_hospital, id_pessoa)\n"
                                        + "VALUES(?, ?, ?)\n";
            inserirNaTabelaPaciente(paciente, conexao, QUERY_PACIENTE);

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            fecharConexaoComBancoDeDados(conexao);
        }

        return findById(paciente.getIdPaciente());
    }

    @Override
    public Paciente update(Integer idPaciente, Paciente pacienteModificado) throws BancoDeDadosException {
        Connection conexao = null;

        try {
            conexao = ConexaoBancoDeDados.getConnection();

            Paciente pacienteAtualizado = findById(idPaciente);

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PESSOA SET \n");

            List<String> camposAtualizados = new ArrayList<>();
            if (pacienteModificado != null) {
                if (pacienteModificado.getNome() != null) {
                    camposAtualizados.add("nome = ?");
                }
                if (pacienteModificado.getCep() != null) {
                    camposAtualizados.add("cep = ?");
                }
                if (pacienteModificado.getDataNascimento() != null) {
                    camposAtualizados.add("data_nascimento = ?");
                }
                if (pacienteModificado.getCpf() != null) {
                    camposAtualizados.add("cpf = ?");
                }
                if (pacienteModificado.getSalarioMensal() != null) {
                    camposAtualizados.add("salario_mensal = ?");
                }
            }

            if(camposAtualizados.isEmpty()){
                return null;
            }

            sql.append(String.join(", ", camposAtualizados));
            sql.append(" WHERE id_pessoa = ?");

            PreparedStatement st = conexao.prepareStatement(sql.toString());

            int index = 1;
            if (pacienteModificado != null) {
                if (pacienteModificado.getNome() != null) {
                    st.setString(index++, pacienteModificado.getNome());
                }
                if (pacienteModificado.getCep() != null) {
                    st.setString(index++, pacienteModificado.getCep());
                }
                if (pacienteModificado.getDataNascimento() != null) {
                    st.setDate(index++, Date.valueOf(pacienteModificado.getDataNascimento()));
                }
                if (pacienteModificado.getCpf() != null) {
                    st.setString(index++, pacienteModificado.getCpf());
                }
                if (pacienteModificado.getSalarioMensal() != null) {
                    st.setDouble(index++, pacienteModificado.getSalarioMensal());
                }
            }

            st.setInt(index++, pacienteAtualizado.getIdPessoa());

            int res = st.executeUpdate();

            return pacienteAtualizado;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            fecharConexaoComBancoDeDados(conexao);
        }
    }

    @Override
    public boolean deleteById(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Paciente paciente = findById(id);

            String sql = "DELETE FROM PACIENTE WHERE ID_PACIENTE = ?";

            PreparedStatement stPaciente = con.prepareStatement(sql);

            stPaciente.setInt(1, id);

            int resPaciente = stPaciente.executeUpdate();
            int resPessoa = 0;
            if (resPaciente > 0){
                String sqlPessoa = "DELETE FROM PESSOA WHERE ID_PESSOA = ?";
                PreparedStatement stPessoa = con.prepareStatement(sqlPessoa);
                stPessoa.setInt(1, paciente.getIdPessoa());
                resPessoa =stPessoa.executeUpdate();
            }else {
                throw new SQLException("Ocorreu um erro na operação");
            }

            return resPessoa > 0;
        }catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Integer getProximoId(Connection connection, String nextSequence) throws SQLException {
        try {
            String sql = "SELECT " + nextSequence + " mysequence from DUAL";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if (res.next()) {
                return res.getInt("mysequence");
            }

            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    public boolean buscarCpf(Paciente paciente){
        Connection con = null;
        boolean retorno = false;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT * FROM Pessoa " +
                    "WHERE cpf = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, paciente.getCpf());

            ResultSet rs = st.executeQuery();

            if (rs.next()){
                retorno = true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return retorno;
    }

    private Paciente obterPaciente(ResultSet res) throws SQLException {
        Integer idPessoa = res.getInt("id_pessoa");
        String nome = res.getString("nome");
        String cep = res.getString("cep");
        LocalDate data = res.getDate("data_nascimento").toLocalDate();
        String cpf = res.getString("cpf");
        Double salarioMensal = res.getDouble("salario_mensal");
        Integer idPaciente = res.getInt("id_paciente");
        Integer id_hospital = res.getInt("id_hospital");

        String dataFormatada = data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        Paciente paciente = new Paciente(nome, cep, dataFormatada, cpf, salarioMensal, id_hospital);

        paciente.setIdPessoa(idPessoa);
        paciente.setIdPaciente(idPaciente);

        return paciente;
    }

    private void fecharConexaoComBancoDeDados(Connection conexao) throws BancoDeDadosException {
        try{
            if(conexao != null){
                conexao.close();
            }
        }catch (SQLException e){
            throw new BancoDeDadosException(e.getCause());
        }
    }

    private void inserirNaTabelaPessoa(Paciente paciente, Connection conexao, String query) throws SQLException {
        Integer idPessoa = this.getProximoId(conexao, "seq_pessoa.nextval");
        paciente.setIdPessoa(idPessoa);

        PreparedStatement stPesssoa = conexao.prepareStatement(query);
        stPesssoa.setInt(1, paciente.getIdPessoa());
        stPesssoa.setString(2, paciente.getNome());
        stPesssoa.setString(3, paciente.getCep());
        stPesssoa.setDate(4, Date.valueOf(paciente.getDataNascimento()));
        stPesssoa.setString(5, paciente.getCpf());
        stPesssoa.setDouble(6, paciente.getSalarioMensal());

        int pessoasInseridas = stPesssoa.executeUpdate();

        if (pessoasInseridas == 0)
            throw new SQLException("Ocorreu um erro ao inserir!");
    }

    private void inserirNaTabelaPaciente(Paciente paciente, Connection conexao, String query) throws SQLException {
        Integer proximoPacienteId = this.getProximoId(conexao, "seq_paciente.nextval");
        paciente.setIdPaciente(proximoPacienteId);

        PreparedStatement stPaciente = conexao.prepareStatement(query);
        stPaciente.setInt(1, paciente.getIdPaciente());
        stPaciente.setInt(2, 1);
        stPaciente.setInt(3, paciente.getIdPessoa());

        int res = stPaciente.executeUpdate();
    }

}
