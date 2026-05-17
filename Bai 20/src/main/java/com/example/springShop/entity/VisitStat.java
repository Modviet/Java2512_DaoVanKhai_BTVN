package com.example.springShop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "visit_stats")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitStat extends BaseEntity{

        @Column(name = "visit_count",nullable = false)
        @Builder.Default
        private Long visitCount = 0L;

}
