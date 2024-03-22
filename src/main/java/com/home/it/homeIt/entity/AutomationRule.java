package com.home.it.homeIt.entity;

import com.home.it.homeIt.enums.AutomatedActionEnum;
import com.home.it.homeIt.enums.RuleConditionTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
public class AutomationRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private RuleConditionTypeEnum condition;
    @Enumerated(EnumType.STRING)
    private AutomatedActionEnum action;

    @ManyToOne
    private SmartDevice device;
}