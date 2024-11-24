package com.example.hanghaepluspretask.common.base;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, U> extends JpaRepository<T, U> {

	List<T> findByDeletedAtIsNull();

	Optional<T> findByIdAndDeletedAtIsNull(U id);
	default @NonNull T getByIdAndDeletedAtIsNull(@NonNull U id) {
		return findByIdAndDeletedAtIsNull(id).orElseThrow(EntityNotFoundException::new);
	}
}
