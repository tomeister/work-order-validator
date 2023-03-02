package org.god.wov.web.common.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "validatedWorkOrders")
public class WorkOrderValidationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String dateReceived;
    @NonNull
    private String status;
    @NonNull
    private String type;
    @NonNull
    private String department;

}
