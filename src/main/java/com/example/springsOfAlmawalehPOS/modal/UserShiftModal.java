package com.example.springsOfAlmawalehPOS.modal;

import lombok.*;

import java.time.LocalDateTime;

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
