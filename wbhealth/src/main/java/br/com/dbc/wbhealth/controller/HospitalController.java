package br.com.dbc.wbhealth.controller;

import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
import br.com.dbc.wbhealth.model.entity.Hospital;
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
public class HospitalController{

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public ResponseEntity<List<Hospital>> findAll() throws BancoDeDadosException {
        return ResponseEntity.status(HttpStatus.OK).body(hospitalService.findAll());
    }

    @GetMapping("/{idHospital}")
    public ResponseEntity<Hospital> findById(@Positive @PathVariable Integer idHospital) {
        Hospital hospital = null;
        try {
            hospital = hospitalService.findById(idHospital);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(hospital);
    }

    @PostMapping
    public ResponseEntity<Hospital> save(@Valid @RequestBody Hospital hospital) {
        Hospital hospitalSalvo = null;
        try {
            hospitalSalvo = hospitalService.save(hospital);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ResponseEntity.status(HttpStatus.CREATED).body(hospitalSalvo);
    }
    @PutMapping("/{idHospital}")
    public ResponseEntity<Hospital> update(@Positive @PathVariable Integer idHospital, @Valid @RequestBody Hospital hospital){
        Hospital hospitalAtualizado = null;
        try {
            hospitalAtualizado = hospitalService.update(idHospital, hospital);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(hospitalAtualizado);
    }

    @DeleteMapping("/{idHospital}")
    public ResponseEntity<Boolean> deleteById(@Positive @PathVariable Integer idHospital){
        try {
            hospitalService.deleteById(idHospital);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
