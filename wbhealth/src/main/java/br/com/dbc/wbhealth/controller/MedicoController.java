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
    public ArrayList<Medico> findAll () {
            ArrayList<Medico> medicos = new ArrayList<>();
            try {
                medicos= medicoService.findAll();
            } catch (BancoDeDadosException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }catch (Exception e){
                e.printStackTrace();
            }
            return medicos;
        }

    @GetMapping("{id}")
    public Medico findById(@PathVariable int id){
        Medico medico = new Medico();
        try {
            return medicoService.findById(id);
        } catch (BancoDeDadosException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            e.printStackTrace();
        }
        return medico;
    }

    @PostMapping()
    public Medico save(@RequestBody Medico medico){
        return medicoService.save(medico);
    }

    @PutMapping("{id}")
    public Medico update(@PathVariable int id, @RequestBody Medico medico){
        Medico medicoAtualizado = new Medico();
        try {
            return medicoService.update(id, medico);
        } catch (BancoDeDadosException e) {
            throw new RuntimeException(e);
        } catch (Exception e ){
            e.printStackTrace();
        }
        return medicoAtualizado;
    }

    @DeleteMapping("{id}")
    public String deleteById(@PathVariable int id){
        return medicoService.deletarPeloId(id);
    }
}
