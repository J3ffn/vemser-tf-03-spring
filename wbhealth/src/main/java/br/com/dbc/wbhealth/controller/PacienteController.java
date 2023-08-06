//package br.com.dbc.wbhealth.controller;
//
//import br.com.dbc.wbhealth.exceptions.BancoDeDadosException;
//import br.com.dbc.wbhealth.model.entity.Paciente;
//import br.com.dbc.wbhealth.service.PacienteService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/paciente")
//public class PacienteController {
//    private final PacienteService pacienteService;
//
//    public PacienteController(PacienteService pacienteService){
//        this.pacienteService = pacienteService;
//    }
//
//    @GetMapping
//    public List<Paciente> list() throws BancoDeDadosException {
//        return pacienteService.listarTodos();
//    }
//
//    @GetMapping("/")
//    public Paciente listById(@RequestParam("idPaciente") Integer idPaciente) throws BancoDeDadosException {
//        return pacienteService.listarPeloId(idPaciente);
//    }
//
//    @PostMapping
//    public Paciente create(@RequestBody Paciente paciente){
//        return pacienteService.inserir(paciente);
//    }
//
//    @PutMapping("/{idPaciente}")
//    public Paciente update(@PathVariable Integer idPaciente, @RequestBody Paciente paciente)
//            throws BancoDeDadosException {
//        return pacienteService.alterarPeloId(idPaciente, paciente);
//    }
//
//    @DeleteMapping("/{idPaciente}")
//    public void delete(@PathVariable Integer idPaciente){
//        pacienteService.deletarPeloId(idPaciente);
//    }
//
//}
