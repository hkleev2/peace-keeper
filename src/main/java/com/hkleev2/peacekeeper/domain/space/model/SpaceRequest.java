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
public class SpaceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "space_id", nullable = false)
    private Long spaceId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @AttributeOverride(name = "value", column = @Column(name = "state"))
    private RequestState state;

    @CreatedDate
    @AttributeOverride(name = "value", column = @Column(name = "created_datetime"))
    private CreatedDateTime createdDateTime;

    public void changeState(RequestState requestState) {
        this.state = requestState;
    }
}
