package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.JobDTO;
import com.example.demo.model.Job;
import com.example.demo.service.JobService;

@Controller
@RequestMapping("/recruitList")
//クラスレベルに @RequestMapping("/recruit") をつける意味は、
//そのコントローラ内のメソッドに一貫してプレフィックス (/recruit) を付けるためです。
public class JobController {

	private static final Logger logger = LogManager.getLogger(JobController.class);

	@Autowired
	private JobService jobService;

	@GetMapping
	public String getJobs(Model model) {
		List<Job> jobs = jobService.getAllJobs();
		for (Job job : jobs) {
			try {
				String encodedImagePath = URLEncoder.encode(job.getImagePath(), StandardCharsets.UTF_8.toString());
				job.setImagePath(encodedImagePath); // エンコードされたパスを設定
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace(); // エンコーディング失敗時のエラーハンドリング
			}
		}
		model.addAttribute("jobs", jobs);
		return "recruitList";
	}

	@GetMapping("/{id}")
	public String getJobDetail(@PathVariable Long id, Model model) {
		Job job = jobService.getJobById(id);
		model.addAttribute("job", job);
		return "jobDetail";
	}

	@PostMapping()
	public String recruitDate(@ModelAttribute JobDTO jobDto, @ModelAttribute Job job,
			@RequestParam("image") MultipartFile file, Model model, @RequestParam("action") String action) {
		try {
			if ("register".equals(action)) {

				// ファイル名を取得
				String filename = file.getOriginalFilename();
				// 保存先パス
				String filePath = "src/main/resources/static/img/" + filename; // パスを修正（/を追加）

				// ファイルを保存
				Files.write(Paths.get(filePath), file.getBytes());

				// 画像のパスをJobオブジェクトに設定
				job.setImagePath(filename); // JobクラスにimagePathフィールドを追加していることを前提としています

				// Job情報を保存
				jobService.setJob(job); // jobServiceに保存処理を委譲
			} else if ("search".equals(action)) {

				String title = jobDto.getTitle();
				String description = jobDto.getDescription();
				Boolean bookmarkFlag = jobDto.getBookmarkFlag();
				List<Job> jobs = new ArrayList<>();

				if (title.isEmpty() && description.isEmpty()) {
					jobs = jobService.getAllJobs();
				} else {
					jobs = jobService.sharchJob(title, description, bookmarkFlag);

				}
				model.addAttribute("jobs", jobs);
			}

		} catch (Exception e) {
			// エラー時
			e.printStackTrace();
			// エラーメッセージをモデルに追加するなどの処理があればここに追加
		}

		return "recruitList";
	}

}
