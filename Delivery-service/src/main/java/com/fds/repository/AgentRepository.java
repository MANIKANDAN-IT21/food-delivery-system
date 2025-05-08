package com.fds.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fds.model.Agent;

public interface AgentRepository  extends JpaRepository<Agent, Long> {
	
    Optional<Agent> findFirstByStatus(String status);

}
