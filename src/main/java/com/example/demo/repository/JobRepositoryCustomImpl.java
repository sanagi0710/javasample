package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Job;

@Repository
public class JobRepositoryCustomImpl implements JobRepositoryCustom {

	private static final Logger logger = LogManager.getLogger(JobRepositoryCustomImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Job> searchJobs(String title, String description, Boolean bookmarkFlag) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Job> query = cb.createQuery(Job.class);
		Root<Job> job = query.from(Job.class);

		List<Predicate> predicates = new ArrayList<>();

		if (title != null && !title.isEmpty()) {
			predicates.add(cb.like(job.get("title"), "%" + title + "%"));

		}

		if (description != null && !description.isEmpty()) {
			predicates.add(cb.like(job.get("description"), "%" + description + "%"));
		}

		if (bookmarkFlag) {
			predicates.add(cb.equal(job.get("bookmarkFlag"), true)); // trueの場合
		} else {
			predicates.add(cb.equal(job.get("bookmarkFlag"), false)); // falseの場合
		}

		query.select(job).where(cb.and(predicates.toArray(new Predicate[0])));

		return entityManager.createQuery(query).getResultList();

	}

}
