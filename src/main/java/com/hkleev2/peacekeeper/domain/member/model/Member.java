package com.hkleev2.peacekeeper.domain.member.model;

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
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @AttributeOverride(name = "value", column = @Column(name = "nickname", unique = true, nullable = false))
    private Nickname nickname;

    @AttributeOverride(name = "value", column = @Column(name = "email", unique = true, nullable = false))
    private Email email;

    @CreatedDate
    @AttributeOverride(name = "value", column = @Column(name = "created_datetime"))
    private CreatedDateTime createdDateTime;

}
