package br.com.dbc.wbhealth.controller;

import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
import br.com.dbc.wbhealth.model.entity.Medico;
import br.com.dbc.wbhealth.service.MedicoService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/medico")
public class MedicoController {
    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService){
        this.medicoService=medicoService;
    }

    @GetMapping
    public ArrayList<Medico> list () {
            ArrayList<Medico> medicos = new ArrayList<>();
            try {
                medicos= medicoService.listarTodos();
            } catch (BancoDeDadosException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }catch (Exception e){
                e.printStackTrace();
            }
            return medicos;
        }

    @GetMapping("{id}")
    public Medico buscarId(@PathVariable int id){
        Medico medico = new Medico();
        try {
            return medicoService.buscarId(id);
        } catch (BancoDeDadosException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            e.printStackTrace();
        }
        return medico;
    }

    @PostMapping()
    public Medico inserir(@RequestBody Medico medico){
        return medicoService.inserir(medico);
    }

    @PutMapping("{id}")
    public Medico alterarPeloId(@PathVariable int id, @RequestBody Medico medico){
        Medico medicoAtualizado = new Medico();
        try {
            return medicoService.alterarPeloId(id, medico);
        } catch (BancoDeDadosException e) {
            throw new RuntimeException(e);
        } catch (Exception e ){
            e.printStackTrace();
        }
        return medicoAtualizado;
    }

    @DeleteMapping("{id}")
    public String deletarPeloId(@PathVariable int id){
        return medicoService.deletarPeloId(id);
    }
}
