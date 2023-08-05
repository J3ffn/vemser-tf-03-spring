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
        return  hospitalRepository.save(hospital);
    }

    public Hospital update(Integer idHospital, Hospital hospital) throws BancoDeDadosException {
        return  hospitalRepository.update(idHospital, hospital);
    }

    public boolean deleteById(Integer id) throws BancoDeDadosException {
        return hospitalRepository.deleteById(id);
    }
}
