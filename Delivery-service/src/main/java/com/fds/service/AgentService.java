package com.fds.service;

import com.fds.model.Agent;

public interface AgentService {
	
	Agent getAvailableAgent();
	
    void updateAgentStatus(Long agentId, String status);
}
