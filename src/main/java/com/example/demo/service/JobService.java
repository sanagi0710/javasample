package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Job;
import com.example.demo.repository.JobRepository;

@Service
public class JobService {

	@Autowired
	private JobRepository jobRepository;

	public List<Job> getAllJobs() {
		return jobRepository.findAll();
	}

	public Job getJobById(Long id) {
		return jobRepository.findById(id).orElse(null);
	}

	public List<Job> sharchJob(String title, String description, Boolean bookmarkFlag) {
		return jobRepository.searchJobs(title, description, bookmarkFlag);
	}

	public void setJob(Job job) {
		jobRepository.save(job);
	}

	public void toggleBookmark(Long id, Boolean bookmarkFlag) {
		Job job = jobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
		job.setBookmarkFlag(bookmarkFlag);
		jobRepository.save(job);
	}

	public List<Job> findBookmarkedJobs(Boolean bookmarkFlag) {
		return jobRepository.findByBookmarkFlag(bookmarkFlag);
	}

}
