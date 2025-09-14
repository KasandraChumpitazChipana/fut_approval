package pe.edu.vallegrande.vg_ms_futApproval.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.vg_ms_futApproval.application.service.IFutApprovalService;
import pe.edu.vallegrande.vg_ms_futApproval.domain.model.FutApproval;
import pe.edu.vallegrande.vg_ms_futApproval.infraestructure.repository.FutApprovalRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class FutApprovalServiceImpl implements IFutApprovalService {

    private final FutApprovalRepository repository;

    @Override
    public Mono<FutApproval> createApproval(FutApproval approval) {
        approval.setId(UUID.randomUUID().toString());
        approval.setCreatedAt(LocalDateTime.now());
        return repository.save(approval);
    }

    @Override
    public Mono<FutApproval> updateApproval(String id, FutApproval approval) {
        return repository.findById(id)
                .flatMap(existing -> {
                    approval.setId(existing.getId());
                    approval.setCreatedAt(existing.getCreatedAt());
                    return repository.save(approval);
                });
    }

    @Override
    public Mono<Void> deleteApproval(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<FutApproval> getApprovalById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<FutApproval> getAllApprovals() {
        return repository.findAll();
    }

    @Override
    public Flux<FutApproval> getByFutRequestId(String futRequestId) {
        return repository.findByFutRequestId(futRequestId);
    }
}
