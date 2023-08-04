package br.com.dbc.wbhealth.controller;

import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
import br.com.dbc.wbhealth.model.entity.Paciente;
import br.com.dbc.wbhealth.service.PacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Paciente findById(@RequestParam("idPaciente") Integer idPaciente) throws BancoDeDadosException {
        return pacienteService.findById(idPaciente);
    }

    @PostMapping
    public Paciente save(@RequestBody Paciente paciente) throws BancoDeDadosException {
        return pacienteService.save(paciente);
    }

    @PutMapping("/{idPaciente}")
    public Paciente update(@PathVariable Integer idPaciente, @RequestBody Paciente paciente)
            throws BancoDeDadosException {
        return pacienteService.update(idPaciente, paciente);
    }

    @DeleteMapping("/{idPaciente}")
    public void delete(@PathVariable Integer idPaciente) throws BancoDeDadosException {
        pacienteService.deleteById(idPaciente);
    }

}
