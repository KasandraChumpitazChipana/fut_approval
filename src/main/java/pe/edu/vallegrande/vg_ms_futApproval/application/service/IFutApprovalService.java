package pe.edu.vallegrande.vg_ms_futApproval.application.service;

import pe.edu.vallegrande.vg_ms_futApproval.domain.model.FutApproval;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFutApprovalService {

    Mono<FutApproval> createApproval(FutApproval approval);
    Mono<FutApproval> updateApproval(String id, FutApproval approval);
    Mono<Void> deleteApproval(String id);
    Mono<FutApproval> getApprovalById(String id);
    Flux<FutApproval> getAllApprovals();
    Flux<FutApproval> getByFutRequestId(String futRequestId);
}
