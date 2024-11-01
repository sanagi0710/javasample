package com.example.demo.repository;

import java.util.List;

//JpaRepository はインターフェース クラスに継承不可
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>, JobRepositoryCustom {
	List<Job> findByBookmarkFlag(boolean bookmarkFlag);

	List<Job> findByCategoriesContaining(String category);
}
