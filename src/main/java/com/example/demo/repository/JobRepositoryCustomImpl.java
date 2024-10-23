package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Job;

@Repository
public class JobRepositoryCustomImpl implements JobRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Job> searchJobs(String title, String description) {
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

		query.select(job).where(cb.or(predicates.toArray(new Predicate[0])));

		return entityManager.createQuery(query).getResultList();

	}

}
