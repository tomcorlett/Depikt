package corlett.depikt.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import corlett.depikt.dev.model.Member;
import corlett.depikt.dev.model.Role;
import corlett.depikt.dev.repo.MemberRepo;
import corlett.depikt.dev.repo.RoleRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

@Service
public class MemberServiceImpl implements MemberService, UserDetailsService {
    private final MemberRepo memberRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(MemberRepo memberRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.memberRepo = memberRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepo.findByEmail(username);
        if (member == null) {
            System.out.println("user " + username + " not found in database ::");
            throw new UsernameNotFoundException("user " + username + " not found in database");
        } else {
            System.out.println("user " + username + " found in database ::" + member.toString());
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        member.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(member.getEmail(), member.getPassword(), authorities);
    }
    
    public Member getMember(Long memberId) {
        return memberRepo.findById(memberId).get();
    }

    public Member findByEmail(String email) {
        return memberRepo.findByEmail(email);
    }

    public List<Member> getMembers() {
        return memberRepo.findAll();
    }

    public void addMember(Member member) {
        System.out.println("now in service addmember");
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepo.save(member);
    }

    @Transactional
    public void updateMember(Long memberId, Member member) {
        Member memberFromId = memberRepo.findById(memberId).orElseThrow(() -> new IllegalStateException("Member with ID " + memberId + " does not exist"));
        memberFromId.updateMember(member);
    }

    public void deleteMember(Long memberId) {
        memberRepo.deleteById(memberId);
    }

    //Role api

    @Override
    @Transactional
    public void addRoleToMember(Long memberId, String roleName) {
        Member member = memberRepo.getById(memberId);
        Role role = roleRepo.findByName(roleName);
        if (!member.getRoles().contains(role)) member.getRoles().add(role);        
    }   

    @Override
    public void addRole(Role role) {
        roleRepo.save(role);
    }
}
