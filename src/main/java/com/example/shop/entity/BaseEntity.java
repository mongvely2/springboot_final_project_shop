package com.example.shop.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    //    생성된 시간을 기록하기 위한 컬럼이라는 어노테이션
    @CreationTimestamp
//    updatable = false: 업데이트가 발생했을 땐 가만히 있어라 라는 어노테이션
    @Column(updatable = false)
    private LocalDateTime createdTime;
    //    수정이 발생됐을 때 기록하기 위한 컬럼이라는 어노테이션
    @UpdateTimestamp
//    insertable = false: insert가 발생했을 땐 가만히 있으라는 어노테이션
    @Column(insertable = false)
    private LocalDateTime updatedTime;

}
