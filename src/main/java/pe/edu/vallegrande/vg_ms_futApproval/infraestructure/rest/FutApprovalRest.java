package pe.edu.vallegrande.vg_ms_futApproval.infraestructure.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.vg_ms_futApproval.application.service.IFutApprovalService;
import pe.edu.vallegrande.vg_ms_futApproval.domain.model.FutApproval;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/v1/futapprovals")
@RequiredArgsConstructor
public class FutApprovalRest {

    private final IFutApprovalService service;

    // Crear aprobación (POST /fut/requests)
    @PostMapping
    public Mono<ResponseEntity<FutApproval>> create(@RequestBody FutApproval approval) {
        return service.createApproval(approval)
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public Mono<ResponseEntity<FutApproval>> getById(@PathVariable String id) {
        return service.getApprovalById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Listar todas
    @GetMapping
    public Flux<FutApproval> getAll() {
        return service.getAllApprovals();
    }

    // Actualizar revisión (PUT /fut/requests/{id}/review)
    @PutMapping("/{id}/review")
    public Mono<ResponseEntity<FutApproval>> review(@PathVariable String id, @RequestBody FutApproval approval) {
        approval.setReviewDate(LocalDateTime.now());
        return service.updateApproval(id, approval)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Registrar entrega (POST /fut/requests/{id}/deliver)
    @PostMapping("/{id}/deliver")
    public Mono<ResponseEntity<FutApproval>> deliver(@PathVariable String id, @RequestBody FutApproval approval) {
        approval.setDeliveredAt(LocalDateTime.now());
        return service.updateApproval(id, approval)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Buscar aprobaciones por enrollment (GET /fut/requests/by-enrollment/{studentEnrollmentId})
    @GetMapping("/by-enrollment/{futRequestId}")
    public Flux<FutApproval> getByEnrollment(@PathVariable String futRequestId) {
        return service.getByFutRequestId(futRequestId);
    }

    // Eliminar aprobación
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return service.deleteApproval(id)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
