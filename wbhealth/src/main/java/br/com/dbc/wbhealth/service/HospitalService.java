package br.com.dbc.wbhealth.service;

import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
import br.com.dbc.wbhealth.model.entity.Hospital;
import br.com.dbc.wbhealth.repository.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository){
        this.hospitalRepository = hospitalRepository;
    }

    public List<Hospital> listarTodos() throws BancoDeDadosException {
        return hospitalRepository.listarTodos();
    }

    public Hospital listarPeloId(Integer id) throws BancoDeDadosException {
        return hospitalRepository.listarPeloId(id);
    }

    public Hospital cadastrar(Hospital hospital) {
            hospitalRepository.cadastrar(hospital);
        return hospital;
    }

    public Hospital alterarPeloId(Integer idHospital, Hospital hospital) {
        Hospital hospitalEncontrado = null;
        try {
            hospitalEncontrado = find(idHospital);
            hospitalEncontrado.setNome(hospital.getNome());
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return hospitalEncontrado;
    }

    public void deletarPeloId(Integer id) {
        try{
            Hospital hospitalEncontrado = find(id);
            hospitalRepository.deletarPeloId(hospitalEncontrado.getIdHospital());
        }catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Hospital find(Integer id) throws Exception {
        Hospital hospitalEncontrado = hospitalRepository.listarTodos().stream()
                .filter(hospital -> hospital.getIdHospital().equals(id))
                .findFirst()
                .get();
 //               .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada!"));
        return hospitalEncontrado;
    }
}
