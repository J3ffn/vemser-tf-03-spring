package br.com.dbc.wbhealth.controller;

import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
import br.com.dbc.wbhealth.model.entity.Atendimento;
import br.com.dbc.wbhealth.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequestMapping("/atendimento")
public class AtendimentoController {

    private AtendimentoService atendimentoService;

    @Autowired
    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    @GetMapping
    public ResponseEntity<List<Atendimento>> findAll() throws BancoDeDadosException {
        return ResponseEntity.status(HttpStatus.OK).body(atendimentoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Atendimento> save(@Valid @RequestBody Atendimento novoAtendimento) throws BancoDeDadosException {
        return ResponseEntity.status(HttpStatus.CREATED).body(atendimentoService.save(novoAtendimento));
    }

    @GetMapping("/{idAtendimento}")
    public ResponseEntity<Atendimento> findById(@Positive(message = "Deve ser positivo") @PathVariable Integer idAtendimento) throws BancoDeDadosException {
        return ResponseEntity.status(HttpStatus.OK).body(atendimentoService.getAtendimentoPeloId(idAtendimento));
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<Atendimento>> findByIdUsuario(@Positive(message = "Deve ser positivo") @PathVariable Integer idPaciente) throws BancoDeDadosException {
        return ResponseEntity.status(HttpStatus.OK).body(atendimentoService.findByIdPaciente(idPaciente));
    }

    @PutMapping("/{idAtendimento}")
    public ResponseEntity<Atendimento> update(@Positive(message = "Deve ser positivo") @PathVariable Integer idAtendimento, @Valid @RequestBody Atendimento atendimento) throws BancoDeDadosException {
        return ResponseEntity.status(HttpStatus.OK).body(atendimentoService.update(idAtendimento, atendimento));
    }

    @DeleteMapping("/{idAtendimento}")
    public ResponseEntity<Void> deleteById(@Positive(message = "Deve ser positivo") @PathVariable Integer idAtendimento) {
        atendimentoService.deleteById(idAtendimento);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
