package corlett.depikt.dev.service;

import corlett.depikt.dev.model.Member;
import corlett.depikt.dev.model.Role;

import java.util.List;

public interface MemberService {
    Member getMember(Long memberId);
    List<Member> getMembers();
    void addMember(Member member);
    void updateMember(Long memberId, Member member);
    void deleteMember(Long memberId);
    void addRoleToMember(Long memberId, String roleName);
    void addRole(Role role);
    Member findByEmail(String email);
}
