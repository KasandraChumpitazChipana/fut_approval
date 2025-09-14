package pe.edu.vallegrande.vg_ms_futApproval.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "fut_approvals")

public class FutApproval {
    @Id
    private String id;
    private String futRequestId;
    private String reviewedBy;
    private LocalDateTime reviewDate;
    private String decision; // Approved, Rejected
    private String reviewComments;
    private Map<String, Object> generatedDocuments;
    private String deliveryMethod; // Pickup, email, postal, digital_platform
    private String deliveryAddress;
    private LocalDateTime deliveredAt;
    private String deliveredBy;
    private String receivedBy;
    private LocalDateTime createdAt;
}
