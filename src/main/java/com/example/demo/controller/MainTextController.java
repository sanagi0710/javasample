package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Message;
import com.example.demo.service.MessageService;

@Controller
public class MainTextController {

	private List<String> messageList = new ArrayList<>();

	private final MessageService messageServise;

	@Autowired
	public MainTextController(MessageService messageServise) {
		this.messageServise = messageServise;
	}

	//	複数のHTTPメソッドを処理する場合: 同じURLでGETとPOSTなどの異なるメソッドを処理する場合は、
	//	@RequestMappingを使用することが適切です。この場合、リクエストメソッドを指定する必要があります。
	@GetMapping("/")
	public String index(Model model) {
		List<Message> messageList = messageServise.getAllMessage();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//Principalからログインユーザの情報を取得
		String userName = auth.getName();
		model.addAttribute("username", userName);
		model.addAttribute("msg", "メッセージを入力してください");
		model.addAttribute("registrationMessages", messageList);
		return "index";
	}

	@PostMapping("/confirm")
	public String confirm(@RequestParam(name = "inputMessage", required = false) String inputMessage, Model model) {
		if (inputMessage != null && !inputMessage.isEmpty()) {
			// 新しいメッセージをリストに追加
			messageList.add(inputMessage);
		}
		model.addAttribute("submittedMessage", inputMessage != null ? inputMessage : "メッセージがありません");
		model.addAttribute("msg", "メッセージを入力してください");
		model.addAttribute("messages", messageList);
		return "index";
	}

	@PostMapping("/save")
	public String createMessage() {
		List<String> messagesToSave = new ArrayList<>(messageList);
		for (String msg : messageList) {
			Message message = new Message();
			message.setContent(msg);
			messageServise.saveMessage(message);
		}
		messageList.clear();
		return "redirect:/";
	}

	@PostMapping("/delete")
	public ResponseEntity<String> deleteMessage() {
		messageServise.deleteAllMessage();
		return ResponseEntity.ok("ok");
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOneMessage(@PathVariable Long id) {
		messageServise.deleteMessage(id);
		return ResponseEntity.ok("ok");
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<String> updateMessage(@PathVariable Long id, @RequestBody Message updatedMessage) {
		messageServise.updateMessage(id, updatedMessage);
		return ResponseEntity.ok("メッセージが更新されました");
	}

}
