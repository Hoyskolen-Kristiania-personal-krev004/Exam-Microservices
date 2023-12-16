package no.exam.contractservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime creationDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @Column(nullable = false)
    private Long ownerId;
    @Column(nullable = false)
    private Long renterId;
    @Column(nullable = false)
    private Long rentableId;
    private int rentalCost;
}
