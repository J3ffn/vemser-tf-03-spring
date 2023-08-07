package br.com.dbc.wbhealth.controller;

import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
import br.com.dbc.wbhealth.model.dto.MedicoInputDTO;
import br.com.dbc.wbhealth.model.dto.MedicoOutputDTO;
import br.com.dbc.wbhealth.model.entity.Medico;
import br.com.dbc.wbhealth.service.MedicoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Validated
@RestController
@RequestMapping("/medico")
public class MedicoController {
    private final MedicoService medicoService;

    @Autowired
    private ObjectMapper objectMapper;

    public MedicoController(MedicoService medicoService){
        this.medicoService=medicoService;
    }



    @GetMapping
    public ResponseEntity<ArrayList<MedicoOutputDTO>> findAll () {
            ArrayList<MedicoOutputDTO> medicoOutputDTOS = new ArrayList<>();
            try {
                medicoOutputDTOS= medicoService.findAll();
            } catch (BancoDeDadosException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }catch (Exception e){
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.OK).body(medicoOutputDTOS);
        }

    @GetMapping("{id}")
    public ResponseEntity<MedicoOutputDTO> findById(@PathVariable int id){
        MedicoOutputDTO medicoOutputDTO = new MedicoOutputDTO();
        try {
            medicoOutputDTO= medicoService.findById(id);
        } catch (BancoDeDadosException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(medicoOutputDTO);
    }

    @PostMapping()
    public ResponseEntity<MedicoOutputDTO> save(@Valid @RequestBody MedicoInputDTO medicoInputDTO){
        return ResponseEntity.status(HttpStatus.OK).body(medicoService.save(medicoInputDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<MedicoOutputDTO> update(@PathVariable int id, @Valid @RequestBody MedicoInputDTO medicoInputDTO){
        MedicoOutputDTO medicoOutputDTO = new MedicoOutputDTO();
        try {
            medicoOutputDTO = medicoService.update(id, medicoInputDTO);
        } catch (BancoDeDadosException e) {
            throw new RuntimeException(e);
        } catch (Exception e ){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(medicoOutputDTO);
    }

    @DeleteMapping("{id}")
    public String deleteById(@PathVariable int id){
        return medicoService.deletarPeloId(id);
    }
}
