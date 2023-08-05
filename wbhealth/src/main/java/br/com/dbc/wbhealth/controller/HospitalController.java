package br.com.dbc.wbhealth.controller;

import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
import br.com.dbc.wbhealth.model.entity.Hospital;
import br.com.dbc.wbhealth.service.HospitalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/hospital")
@Validated
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/listar-todos")
    public List<Hospital> findAll() throws BancoDeDadosException {
            return hospitalService.findAll();
    }

    @GetMapping("/listar-pelo-id/{idHospital}")
    public Hospital findById(@Positive @PathVariable Integer id) throws BancoDeDadosException { /////
        return hospitalService.findById(id);
    }

    @PostMapping("/cadastrar")
    public Hospital save(@Valid @RequestBody Hospital hospital) throws BancoDeDadosException {
        return hospitalService.save(hospital);
    }
    @PutMapping("/alterar{idHospital}")
    public ResponseEntity<Hospital> update(@Positive @PathVariable Integer id, @Valid @RequestBody Hospital hospital){
        return ResponseEntity.status(HttpStatus.OK).body(hospitalService.update(id, hospital));
    }

    @PutMapping("/deletar/{idHospital}")
    public ResponseEntity deleteById(@Positive @PathVariable Integer id){
        hospitalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
