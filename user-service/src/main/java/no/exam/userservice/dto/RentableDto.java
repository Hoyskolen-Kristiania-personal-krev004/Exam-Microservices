package no.exam.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentableDto {
    private Long rentableId;
    private String rentableName;
    private String rentableDesc;
    private Long ownerId;
}
