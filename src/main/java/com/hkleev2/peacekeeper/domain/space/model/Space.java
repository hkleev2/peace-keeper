package com.hkleev2.peacekeeper.domain.space.model;

import com.hkleev2.peacekeeper.domain.common.CreatedDateTime;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "space_key", nullable = false)
    private String spaceKey;

    @AttributeOverride(name = "value", column = @Column(name = "spacename", unique = true, nullable = false))
    private Spacename spacename;

    @Column(name = "admin_member_id", nullable = false)
    private Long adminMemberId;

    @CreatedDate
    @AttributeOverride(name = "value", column = @Column(name = "created_datetime"))
    private CreatedDateTime createdDateTime;
}
