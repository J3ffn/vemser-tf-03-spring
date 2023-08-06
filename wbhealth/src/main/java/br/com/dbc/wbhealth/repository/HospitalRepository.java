//package br.com.dbc.wbhealth.repository;
//
//import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
//import br.com.dbc.wbhealth.model.entity.Hospital;
//import org.springframework.stereotype.Repository;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class HospitalRepository implements Repositorio<Integer, Hospital> {
//
//    @Override
//    public Integer getProximoId(Connection connection, String nextSequence) throws SQLException {
//        try {
//            String sql = "SELECT " + nextSequence + " mysequence from DUAL";
//            Statement stmt = connection.createStatement();
//            ResultSet res = stmt.executeQuery(sql);
//
//            if (res.next()) {
//                return res.getInt("mysequence");
//            }
//            return null;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        }
//    }
//
//    @Override
//    public Hospital buscarId(Integer id) throws BancoDeDadosException { ////Excluir
//        return null;
//    }
//
//    @Override
//    public List<Hospital> listarTodos() throws BancoDeDadosException {
//        List<Hospital> hospitais = new ArrayList<>();
//        Connection con = null;
//
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//            Statement st = con.createStatement();
//
//            String sql = "SELECT * FROM HOSPITAL\n";
//
//            ResultSet res = st.executeQuery(sql);
//
//            while (res.next()) {
//                Hospital hospital = new Hospital();
//                hospital.setIdHospital(res.getInt("id_hospital"));
//                hospital.setNome(res.getString("nome"));
//                hospitais.add(hospital);
//            }
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return hospitais;
//    }
//
//    @Override
//    public Hospital listarPeloId(Integer id) throws BancoDeDadosException {
//        Hospital hospital = new Hospital();
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//            Statement stmt = con.createStatement();
//
//            String sql = "SELECT * FROM HOSPITAL WHERE ID_HOSPITAL = " + id;
//
//            ResultSet res = stmt.executeQuery(sql);
//
//            hospital.setIdHospital(res.getInt("id_hospital"));
//            hospital.setNome(res.getString("nome"));
//
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return hospital;
//    }
//
//    @Override
//    public void cadastrar(Hospital hospital){ ////Deve retornar hospital
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//
//            Integer proximoHospitalId = this.getProximoId(con, "seq_hospital.nextval");
//            hospital.setIdHospital(proximoHospitalId);
//
//            String sqlHospital = "INSERT INTO Hospital\n" +
//                    "(id_hospital, nome)\n" +
//                    "VALUES(?, ?)\n";
//
//            PreparedStatement stHospital = con.prepareStatement(sqlHospital);
//
//            stHospital.setInt(1, hospital.getIdHospital());
//            stHospital.setString(2, hospital.getNome());
//
//            int hospitaisInseridos = stHospital.executeUpdate();
//            if (hospitaisInseridos == 0) throw new SQLException("Ocorreu um erro ao inserir!");
////            return hospital;
//        } catch (BancoDeDadosException e) {
//            System.err.println("Erro ao acessar o banco de dados:");
//            e.printStackTrace();
//        } catch (Exception e) {
//            System.err.println("Erro inesperado:");
//            e.printStackTrace();
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public boolean alterarPeloId(Integer id, Hospital hospital) throws BancoDeDadosException {
//
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//            Hospital hospital1Id = this.listarPeloId(id);
//            StringBuilder sql = new StringBuilder();
//            sql.append("UPDATE HOSPITAL SET nome = ?\n");
//            sql.append("WHERE HOSPITAL.id_hospital = ?");
//
//            PreparedStatement st = con.prepareStatement(sql.toString());
//
//            st.setString(1, hospital.getNome());
//            st.setInt(2, id);
//
//            int res = st.executeUpdate();
//            return res > 0;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new BancoDeDadosException(e.getCause());
//            }
//        }
//    }
//
//    @Override
//    public boolean deletarPeloId(Integer id) throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//
//            String sql = "DELETE FROM HOSPITAL WHERE id_hospital = ?";
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//
//            stmt.setInt(1, id);
//
//            int res = stmt.executeUpdate();
//
//            return res > 0;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    private Hospital getHospitalFromResultSet(ResultSet res) throws SQLException {
//        Hospital hospital = new Hospital();
//        hospital.setIdHospital(res.getInt("id_hospital"));
//        hospital.setNome(res.getString("nome"));
//        return hospital;
//    }
//}
