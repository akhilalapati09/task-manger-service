package com.taskmanager.service;

import com.taskmanager.model.TeamMember;
import com.taskmanager.repository.TeamMemberRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TeamMemberService {

    @Inject
    TeamMemberRepository teamMemberRepository;

    public List<TeamMember> getAllTeamMembers() {
        return teamMemberRepository.listAll();
    }

    public TeamMember getTeamMemberById(Long id) {
        return teamMemberRepository.findById(id);
    }

    @Transactional
    public TeamMember createTeamMember(TeamMember teamMember) {
        if (teamMember.role == null || teamMember.role.isBlank()) {
            throw new IllegalArgumentException("Role is required");
        }
        if (teamMemberRepository.existsByEmail(teamMember.email)) {
            throw new IllegalArgumentException("Email already in use");
        }
        teamMemberRepository.persist(teamMember);
        return teamMember;
    }

    @Transactional
    public TeamMember updateTeamMember(Long id, TeamMember teamMemberDetails) {
        TeamMember teamMember = teamMemberRepository.findById(id);
        if (teamMember == null) {
            return null;
        }
        
        if (teamMemberDetails.name != null) {
            teamMember.name = teamMemberDetails.name;
        }
        if (teamMemberDetails.email != null && !teamMemberDetails.email.equals(teamMember.email)) {
            if (teamMemberRepository.existsByEmail(teamMemberDetails.email)) {
                throw new IllegalArgumentException("Email already in use");
            }
            teamMember.email = teamMemberDetails.email;
        }
        if (teamMemberDetails.role != null) {
            teamMember.role = teamMemberDetails.role;
        }
        
        return teamMember;
    }

    @Transactional
    public boolean deleteTeamMember(Long id) {
        return teamMemberRepository.deleteById(id);
    }
}
