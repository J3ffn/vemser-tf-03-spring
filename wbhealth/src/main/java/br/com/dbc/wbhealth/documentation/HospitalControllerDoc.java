package br.com.dbc.wbhealth.documentation;

import br.com.dbc.wbhealth.model.dto.hospital.HospitalInputDTO;
import br.com.dbc.wbhealth.model.dto.hospital.HospitalOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface HospitalControllerDoc {

    @Operation(summary = "Listar hospitais", description = "Cria uma lista de todos os hospitais cadastrados no sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista gerada com sucesso!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )

    @GetMapping
    public ResponseEntity<List<HospitalOutputDTO>> findAll();

    @GetMapping("/{idHospital}")
    public ResponseEntity<HospitalOutputDTO> findById(@Positive @PathVariable Integer idHospital);

    @PostMapping
    public ResponseEntity<HospitalOutputDTO> save(@Valid @RequestBody HospitalInputDTO hospital);

    @PutMapping("/{idHospital}")
    public ResponseEntity<HospitalOutputDTO> update(@Positive @PathVariable Integer idHospital, @Valid @RequestBody HospitalInputDTO hospital);

    @DeleteMapping("/{idHospital}")
    public ResponseEntity<Boolean> deleteById(@Positive @PathVariable Integer idHospital);
}
