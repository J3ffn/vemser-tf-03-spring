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
    public List<Hospital> listarTodos() throws BancoDeDadosException {
            return hospitalService.listarTodos();
    }

    @GetMapping("/listar-pelo-id/{idHospital}")
    public Hospital listarPeloID( @Positive @PathVariable Integer id) throws BancoDeDadosException { /////
        return hospitalService.listarPeloId(id);
    }

    @PostMapping("/cadastrar")
    public Hospital cadastrar(@Valid @RequestBody Hospital hospital) {
        return hospitalService.cadastrar(hospital);
    }
    @PutMapping("/alterar{idHospital}")
    public ResponseEntity<Hospital> alterarPeloId(@Positive @PathVariable Integer id, @Valid @RequestBody Hospital hospital){
        return ResponseEntity.status(HttpStatus.OK).body(hospitalService.alterarPeloId(id, hospital));
    }

    @PutMapping("/deletar/{idHospital}")
    public ResponseEntity deletarPeloId(@Positive @PathVariable Integer id){
        hospitalService.deletarPeloId(id);
        return ResponseEntity.noContent().build();
    }
}
