package com.fds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fds.model.Agent;
import com.fds.repository.AgentRepository;

@Service
public class AgentServiceImpl  implements AgentService{
	

    @Autowired
    private AgentRepository agentRepo;

	@Override
	public Agent getAvailableAgent() {
		 return agentRepo.findFirstByStatus("Available")
	                .orElseThrow(() -> new RuntimeException("No available agents"));
	}

	@Override
	public void updateAgentStatus(Long agentId, String status) {
		Agent agent = agentRepo.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found"));
        agent.setStatus(status);
        agentRepo.save(agent);
	}

}
