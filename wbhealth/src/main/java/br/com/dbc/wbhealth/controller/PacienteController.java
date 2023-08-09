package br.com.dbc.wbhealth.controller;

import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
import br.com.dbc.wbhealth.exceptions.EntityNotFound;
import br.com.dbc.wbhealth.model.dto.paciente.PacienteInputDTO;
import br.com.dbc.wbhealth.model.dto.paciente.PacienteOutputDTO;
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
    public List<PacienteOutputDTO> findAll() throws BancoDeDadosException {
        return pacienteService.findAll();
    }

    @GetMapping("/")
    public ResponseEntity<PacienteOutputDTO> findById(@RequestParam("idPaciente") @Positive Integer idPaciente)
            throws BancoDeDadosException, EntityNotFound {
        PacienteOutputDTO pacienteEncontrado = pacienteService.findById(idPaciente);
        return new ResponseEntity<>(pacienteEncontrado, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PacienteOutputDTO> save(@RequestBody @Valid PacienteInputDTO paciente)
            throws BancoDeDadosException {
        PacienteOutputDTO pacienteCriado = pacienteService.save(paciente);
        return new ResponseEntity<>(pacienteCriado, HttpStatus.OK);
    }

    @PutMapping("/{idPaciente}")
    public ResponseEntity<PacienteOutputDTO> update(@PathVariable @Positive Integer idPaciente,
                                                    @RequestBody @Valid PacienteInputDTO paciente)
            throws BancoDeDadosException, EntityNotFound {
        PacienteOutputDTO pacienteAtualizado = pacienteService.update(idPaciente, paciente);
        return new ResponseEntity<>(pacienteAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{idPaciente}")
    public ResponseEntity<Void> delete(@PathVariable @Positive Integer idPaciente)
            throws BancoDeDadosException, EntityNotFound {
        pacienteService.deleteById(idPaciente);
        return ResponseEntity.ok().build();
    }

}
