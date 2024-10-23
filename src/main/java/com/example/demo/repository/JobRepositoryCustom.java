package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Job;

public interface JobRepositoryCustom {
	List<Job> searchJobs(String title, String description);
}
