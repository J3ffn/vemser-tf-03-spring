package br.com.dbc.wbhealth.repository;

import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
import br.com.dbc.wbhealth.model.entity.Atendimento;
import br.com.dbc.wbhealth.model.enumarator.TipoDeAtendimento;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AtendimentoRepository implements Repositorio<Integer, Atendimento> {

    @Override
    public Atendimento save(Atendimento atendimento) throws BancoDeDadosException {
        Connection con = null;
        Atendimento atendicmentoAux = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoAtendimentoId = this.getProximoId(con, "seq_atendimento.nextval");
            atendimento.setIdAtendimento(proximoAtendimentoId);

            String sqlAtendimento = """
                    INSERT INTO Atendimento
                    (id_atendimento, id_hospital, id_paciente, id_medico, data_atendimento, laudo, tipo_de_atendimento, valor_atendimento)
                    VALUES(?, ?, ?, ?, ?, ?, ?, ?)
                    """;

            PreparedStatement stAtendimento = con.prepareStatement(sqlAtendimento);

            stAtendimento.setInt(1, atendimento.getIdAtendimento());
            stAtendimento.setInt(2, atendimento.getIdHospital());
            stAtendimento.setInt(3, atendimento.getIdPaciente());
            stAtendimento.setInt(4, atendimento.getIdMedico());
            stAtendimento.setDate(5, Date.valueOf(atendimento.getDataAtendimento()));
            stAtendimento.setString(6, atendimento.getLaudo());
            stAtendimento.setString(7, atendimento.getTipoDeAtendimento().name());
            stAtendimento.setDouble(8, atendimento.getValorDoAtendimento());


            int atendimentosInseridos = stAtendimento.executeUpdate();


            if (atendimentosInseridos == 0) throw new SQLException("Ocorreu um erro ao inserir!");

            atendicmentoAux = findById(proximoAtendimentoId);
        }catch (BancoDeDadosException e) {
            System.err.println("Erro ao acessar o banco de dados:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro inesperado:");
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return atendicmentoAux;
    }

    // Criar Update

    @Override
    public List<Atendimento> findAll() throws BancoDeDadosException {
        List<Atendimento> atendimentos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM ATENDIMENTO\n";

            ResultSet res = st.executeQuery(sql);

            while (res.next()){
                Integer idAtendimento = res.getInt("id_atendimento");
                Integer idHospital = res.getInt("id_hospital");
                Integer idPaciente = res.getInt("id_paciente");
                Integer idMedico = res.getInt("id_medico");
                LocalDate dataAtendimento = res.getDate("data_atendimento").toLocalDate();
                String laudo = res.getString("laudo");
                TipoDeAtendimento tipoDeAtendimento = TipoDeAtendimento.getTipo(res.getString("tipo_de_atendimento")); // Arrumar aq
//                Double valorAtendimento = res.getDouble("valor_atendimento");

                String dataFormatada = dataAtendimento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                Atendimento atendimento = new Atendimento(idHospital, idPaciente, idMedico, dataAtendimento, laudo, tipoDeAtendimento);

                atendimento.setIdAtendimento(idAtendimento);
                atendimentos.add(atendimento);
            }

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
        return atendimentos;
    }


    public Atendimento findById(Integer id) throws BancoDeDadosException {
        Atendimento atendimento = null;
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            String sql = "SELECT * FROM ATENDIMENTO\n" +
                    "WHERE ATENDIMENTO.id_atendimento = ?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);


            ResultSet res = st.executeQuery();
            if (res.next()){
                Integer idAtendimento = res.getInt("id_atendimento");
                Integer idHospital = res.getInt("id_hospital");
                Integer idPaciente = res.getInt("id_paciente");
                Integer idMedico = res.getInt("id_medico");
                LocalDate dataAtendimento =   res.getDate("data_atendimento").toLocalDate();
                String laudo = res.getString("laudo");
                TipoDeAtendimento tipoDeAtendimento = TipoDeAtendimento.getTipo(res.getString("tipo_de_atendimento"));
                Double valorAtendimento = res.getDouble("valor_atendimento");

                atendimento = new Atendimento(idHospital, idPaciente, idMedico, dataAtendimento, laudo, tipoDeAtendimento);

                atendimento.setIdAtendimento(idAtendimento);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return atendimento;
    }

    @Override
    public Atendimento update(Integer id, Atendimento atendimento) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Atendimento atendimentoId = this.findById(id);

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ATENDIMENTO SET \n");

            List<String> camposAtualizados = new ArrayList<>();
            if (atendimento != null) {
                if (atendimento.getDataAtendimento() != null) {
                    camposAtualizados.add("data_atendimento = ?");
                }
                if (atendimento.getLaudo() != null) {
                    camposAtualizados.add("laudo = ?");
                }
                if (atendimento.getTipoDeAtendimento() != null) {
                    camposAtualizados.add("tipo_de_atendimento = ?");
                }
            }

            if (!camposAtualizados.isEmpty()) {
                sql.append(String.join(", ", camposAtualizados));
                sql.append(" WHERE id_atendimento = ?");

                PreparedStatement st = con.prepareStatement(sql.toString());

                int index = 1;
                if (atendimento != null) {
                    if (atendimento.getDataAtendimento() != null) {
                        st.setDate(index++, Date.valueOf(atendimento.getDataAtendimento()));
                    }
                    if (atendimento.getLaudo() != null) {
                        st.setString(index++, atendimento.getLaudo());
                    }
                    if (atendimento.getTipoDeAtendimento() != null) {
                        st.setString(index++, atendimento.getTipoDeAtendimento().name());
                    }
                }

                st.setInt(index++, atendimentoId.getIdAtendimento());

                int res = st.executeUpdate();

                Atendimento atendimentoAux = findById(id);

                return atendimento;
            } else {
                throw new BancoDeDadosException(new Throwable("Nenhum campo a atualizar"));
            }
        } catch (SQLException e) {
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
    public boolean deleteById(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Atendimento atendimento = findById(id);

            String sql = "DELETE FROM ATENDIMENTO WHERE id_atendimento = ?";

            PreparedStatement stAtendimento = con.prepareStatement(sql);

            stAtendimento.setInt(1, id);

            int resAtendimento = stAtendimento.executeUpdate();
            if (resAtendimento > 0){
                String sqlAtendimento = "DELETE FROM ATENDIMENTO WHERE id_atendimento = ?";
                PreparedStatement stAtendimentos = con.prepareStatement(sqlAtendimento);
                stAtendimentos.setInt(1, atendimento.getIdAtendimento());
                resAtendimento =stAtendimentos.executeUpdate();
            }else {
                throw new SQLException("Ocorreu um erro na operação");
            }
            return resAtendimento > 0;
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




}
