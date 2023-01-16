package com.ofBusiness.chatLogs.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ofBusiness.chatLogs.dto.ChatLogDTO;
import com.ofBusiness.chatLogs.entity.ChatLog;
import com.ofBusiness.chatLogs.repository.ChatLogRepository;

@RestController
@RequestMapping(path = "/chatlogs")
@Validated
public class ChatLogController {

	@Autowired
	private ChatLogRepository chatLogRepository;

	@PostMapping("/{user}")
	public ResponseEntity<Integer> submitChatLog(
			@PathVariable("user") @Size(max = 15, message = "userId length can't be greater than 15") String userId,
			@Valid @RequestBody ChatLogDTO chatLogDTO) {
		ChatLog chatLog = createChatLogEntity(chatLogDTO);
		chatLog.setUser(userId);
		ChatLog savedChatlog = chatLogRepository.save(chatLog);

		return new ResponseEntity<Integer>(savedChatlog.getId(), HttpStatus.CREATED);

	}

	private ChatLog createChatLogEntity(ChatLogDTO chatLogDTO) {
		ChatLog chatLog = new ChatLog();
		chatLog.setActive(true);

		chatLog.setCreated_date(convertEpochToTimestamp(chatLogDTO.getTimestamp()));
		chatLog.setIssent(chatLogDTO.isSent());
		chatLog.setMessage(chatLogDTO.getMessage());
		return chatLog;
	}

	private Timestamp convertEpochToTimestamp(long time) {
		Timestamp timestamp = new Timestamp(time * 1000);
		return timestamp;
	}

	@GetMapping("/{user}")
	public ResponseEntity<List<ChatLog>> getChatLogs(
			@PathVariable("user") @Size(max = 15, message = "userId length can't be greater than 15") String userId,
			@RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit,
			@RequestParam(name = "start", required = false, defaultValue = "1") Integer start) {

		// paging start with 0 index
		start = (start - 1);

		// handled -ve start value
		start = start < 0 ? 0 : start;
		Pageable pageable = PageRequest.of(start, limit);

		List<ChatLog> chatlogs = chatLogRepository.findByUserAndActiveTrueOrderByCreatedDateDesc(userId, pageable);
		return new ResponseEntity<List<ChatLog>>(chatlogs, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{user}")
	public ResponseEntity<String> deleteUser(
			@PathVariable("user") @Size(max = 15, message = "userId length can't be greater than 15") String userId) {
		// TODO before checking we can also check whether record exists or not.
		chatLogRepository.deactivateUser(userId);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);

	}

	@DeleteMapping(path = "/{user}/{msgId}")
	public ResponseEntity<String> deleteUser(
			@PathVariable("user") @Size(max = 15, message = "userId length can't be greater than 15") String userId,
			@PathVariable("msgId") @NotNull Integer msgId) {
		// TODO before checking we can also check whether record exists or not.
		chatLogRepository.deactivateUserMessage(userId, msgId);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);

	}

}
