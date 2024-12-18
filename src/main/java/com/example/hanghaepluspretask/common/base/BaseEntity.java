package com.example.hanghaepluspretask.common.base;

import com.example.hanghaepluspretask.util.DateUtil;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@EntityListeners(AuditingEntityListener.class) // update 리스너
@MappedSuperclass // JPA Entity간에 공통으로 사용되는 매핑 정보 정의함
@Getter
public abstract class BaseEntity {

	@Column(updatable = false)
	@CreatedDate
	private Instant createdAt;

	@LastModifiedDate
	private Instant updatedAt;

	private Instant deletedAt;

	public void softDelete() {
		deletedAt = DateUtil.now();
	}
}
