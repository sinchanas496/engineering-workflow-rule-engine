package com.example.engineeringworkflow.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rules")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private RuleType type;

    @Column(nullable = false)
    private Boolean active;

    @Column(columnDefinition = "TEXT")
    private String errorMessage;

    @Column(length = 20)
    private String sourceStatus;

    @Column(length = 20)
    private String targetStatus;

    @Column(length = 20)
    private String priorityCondition;

    private Integer minApprovals;

    @Column(nullable = false)
    private Integer executionOrder;
}
