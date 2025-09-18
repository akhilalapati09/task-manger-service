package com.taskmanager.service;

import com.taskmanager.model.Member;
import com.taskmanager.repository.MemberRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class MemberService {

    @Inject
    MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.listAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    @Transactional
    public Member createMember(Member member) {
        if (member.role == null || member.role.isBlank()) {
            throw new IllegalArgumentException("Role is required");
        }
        if (memberRepository.existsByEmail(member.email)) {
            throw new IllegalArgumentException("Email already in use");
        }
        memberRepository.persist(member);
        return member;
    }

    @Transactional
    public Member updateMember(Long id, Member memberDetails) {
        Member member = memberRepository.findById(id);
        if (member == null) {
            return null;
        }
        
        if (memberDetails.name != null) {
            member.name = memberDetails.name;
        }
        if (memberDetails.email != null && !memberDetails.email.equals(member.email)) {
            if (memberRepository.existsByEmail(memberDetails.email)) {
                throw new IllegalArgumentException("Email already in use");
            }
            member.email = memberDetails.email;
        }
        if (memberDetails.role != null) {
            member.role = memberDetails.role;
        }
        
        return member;
    }

    @Transactional
    public boolean deleteMember(Long id) {
        return memberRepository.deleteById(id);
    }
}
