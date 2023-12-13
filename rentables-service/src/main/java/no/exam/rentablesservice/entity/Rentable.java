package no.exam.rentablesservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Rentable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentableId;
    private String rentableName;
    private String rentableDescription;
    @Column(nullable = false)
    private Long ownerId;


}
