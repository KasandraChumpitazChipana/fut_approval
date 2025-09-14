package pe.edu.vallegrande.vg_ms_futApproval.infraestructure.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.edu.vallegrande.vg_ms_futApproval.domain.model.FutApproval;
import reactor.core.publisher.Flux;

public interface FutApprovalRepository extends ReactiveMongoRepository<FutApproval, String>  {

    Flux<FutApproval> findByFutRequestId(String futRequestId);
}
