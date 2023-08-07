package br.com.dbc.wbhealth.controller;

import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
import br.com.dbc.wbhealth.model.entity.Paciente;
import br.com.dbc.wbhealth.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService){
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<Paciente> findAll() throws BancoDeDadosException {
        return pacienteService.findAll();
    }

    @GetMapping("/")
    public ResponseEntity<Paciente> findById(@RequestParam("idPaciente") @Positive Integer idPaciente)
            throws BancoDeDadosException {
        Paciente pacienteEncontrado = pacienteService.findById(idPaciente);
        return new ResponseEntity<>(pacienteEncontrado, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Paciente> save(@RequestBody @Valid Paciente paciente)
            throws BancoDeDadosException {
        Paciente novoPaciente = pacienteService.save(paciente);
        return new ResponseEntity<>(novoPaciente, HttpStatus.OK);
    }

    @PutMapping("/{idPaciente}")
    public ResponseEntity<Paciente> update(@PathVariable @Positive Integer idPaciente,
                                           @RequestBody @Valid Paciente paciente)
            throws BancoDeDadosException {
        Paciente pacienteAtualizado =  pacienteService.update(idPaciente, paciente);
        return new ResponseEntity<>(pacienteAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{idPaciente}")
    public ResponseEntity<Void> delete(@PathVariable @Positive Integer idPaciente) throws BancoDeDadosException {
        pacienteService.deleteById(idPaciente);
        return ResponseEntity.ok().build();
    }

}
