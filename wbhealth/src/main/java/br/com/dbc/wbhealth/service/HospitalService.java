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

    public List<Hospital> findAll() throws BancoDeDadosException {
        return hospitalRepository.findAll();
    }

    public Hospital findById(Integer id) throws BancoDeDadosException {
        return hospitalRepository.findById(id);
    }

    public Hospital save(Hospital hospital) throws BancoDeDadosException {
            hospitalRepository.save(hospital);
        return hospital;
    }

    public Hospital update(Integer idHospital, Hospital hospital) {
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

    public void deleteById(Integer id) {
        try{
            Hospital hospitalEncontrado = find(id);
            hospitalRepository.deleteById(hospitalEncontrado.getIdHospital());
        }catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Hospital find(Integer id) throws Exception {
        Hospital hospitalEncontrado = hospitalRepository.findAll().stream()
                .filter(hospital -> hospital.getIdHospital().equals(id))
                .findFirst()
                .get();
 //               .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada!"));
        return hospitalEncontrado;
    }
}
