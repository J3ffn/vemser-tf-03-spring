package br.com.dbc.wbhealth.service;

import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
import br.com.dbc.wbhealth.exceptions.NegocioException;
import br.com.dbc.wbhealth.model.dto.HospitalOutputDTO;
import br.com.dbc.wbhealth.model.dto.HospitalInputDTO;
import br.com.dbc.wbhealth.model.entity.Hospital;
import br.com.dbc.wbhealth.repository.HospitalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final ObjectMapper objectMapper;

    public HospitalService(HospitalRepository hospitalRepository, ObjectMapper objectMapper){
        this.hospitalRepository = hospitalRepository;
        this.objectMapper = objectMapper;
    }

    public List<HospitalOutputDTO> findAll()  {
        try {
            List<Hospital> hospitais= hospitalRepository.findAll();
            return convertListToDTO(hospitais);
        } catch (BancoDeDadosException e){
            throw new NegocioException(e.getMessage());
        }
    }

    public HospitalOutputDTO findById(Integer idHospital){
        try {
            Hospital hospital = hospitalRepository.findById(idHospital);
            validateExist(hospital);
            return convertToDTO(hospital);
        } catch (BancoDeDadosException e){
            throw new NegocioException(e.getMessage());
        }
    }

    public HospitalOutputDTO save(HospitalInputDTO hospitalInputDTO) {
        try {
            Hospital hospital = convertToEntity(hospitalInputDTO);
            Hospital hospitalCadastrado = hospitalRepository.save(hospital);
            return convertToDTO(hospitalCadastrado);
        } catch (BancoDeDadosException e){
            throw new NegocioException(e.getMessage());
        }
    }

    public HospitalOutputDTO update(Integer idHospital, HospitalInputDTO hospitalInputDTO){
        try {
            validateExist(idHospital);
            Hospital hospital = convertToEntity(hospitalInputDTO);
            Hospital hospitalAtualizado = hospitalRepository.update(idHospital, hospital);
            return  convertToDTO(hospitalAtualizado);
        } catch (BancoDeDadosException e){
            throw new NegocioException(e.getMessage());
        }
    }

    public boolean deleteById(Integer idHospital){
        try {
            validateExist(idHospital);
            return hospitalRepository.deleteById(idHospital);
        } catch (BancoDeDadosException e){
            throw new NegocioException(e.getMessage());
        }
    }

    private void validateExist(Integer idHospital) {
        try {
            Hospital hospital = hospitalRepository.findById(idHospital);
            validateExist(hospital);
        } catch (BancoDeDadosException e){
            throw new NegocioException(e.getMessage());
        }
    }

    private void validateExist(Hospital hospital) {
        if (ObjectUtils.isEmpty(hospital)) {
            throw new NegocioException("Hospital não existe");
        }
    }
    private Hospital convertToEntity(HospitalInputDTO hospitalInputDTO) {
        Hospital entity = objectMapper.convertValue(hospitalInputDTO, Hospital.class);
        entity.setNome(hospitalInputDTO.getNome());
        return entity;
    }

    private static HospitalOutputDTO convertToDTO(Hospital hospital) {
        HospitalOutputDTO hospitalOutputDTO = new HospitalOutputDTO();
        hospitalOutputDTO.setIdHospital(hospital.getIdHospital());
        hospitalOutputDTO.setNome(hospital.getNome());
        return hospitalOutputDTO;
    }

    private static List<HospitalOutputDTO> convertListToDTO(List<Hospital> hospitais) {
        List<HospitalOutputDTO> hospitaisDtos = new ArrayList<>();
        for (Hospital hospital : hospitais) {
            hospitaisDtos.add(convertToDTO(hospital));
        }
        return hospitaisDtos;
    }
}
