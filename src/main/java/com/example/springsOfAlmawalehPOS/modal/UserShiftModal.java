package com.example.springsOfAlmawalehPOS.modal;

import com.example.springsOfAlmawalehPOS.entity.ShiftCashManagement;
import com.example.springsOfAlmawalehPOS.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserShiftModal {
    private Long id;
    private LocalDateTime createdAt;
    private Long userId;
    private Long shiftCashManagementId;
    private boolean status;
    private LocalDateTime start_at;
    private LocalDateTime close_at;
    private double startAmount;
    private double closeAmount;
}
