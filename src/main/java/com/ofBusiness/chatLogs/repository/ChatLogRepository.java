package com.ofBusiness.chatLogs.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ofBusiness.chatLogs.entity.ChatLog;

@Repository
@Transactional
public interface ChatLogRepository extends JpaRepository<ChatLog, Long>  {
	
	List<ChatLog> findByUserAndActiveTrueOrderByCreatedDateDesc(String user, Pageable pageable);
	
	//soft delete
	@Modifying
	@Query("update chatlog cl set cl.active = false where cl.user = :user")
	void deactivateUser(@Param("user") String user);

	//soft delete
	@Modifying
	@Query("update chatlog cl set cl.active = false where cl.user = :user and cl.id = :messageId")
	void deactivateUserMessage(@Param("user") String user, @Param("messageId") Integer messageId);
	
}
