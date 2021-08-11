package corlett.depikt.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corlett.depikt.dev.model.Member;
import corlett.depikt.dev.model.Role;
import corlett.depikt.dev.model.RoleToMemberForm;
import corlett.depikt.dev.service.MemberServiceImpl;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/member")
public class MemberController {
    private final MemberServiceImpl memberService;

    @Autowired
    public MemberController(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }

    @GetMapping(path = "{memberId}")
    public Member getMember(@PathVariable("memberId") Long memberId) {
        return memberService.getMember(memberId);
    }

    @GetMapping
    public List<Member> getMembers() {
        System.out.println("get members");
        return memberService.getMembers();
    }

    @PostMapping
    public void addMember(@RequestBody Member member) {
        System.out.println("in addmember " + member.toString());
        memberService.addMember(member);
    }

    @PutMapping(path = "{memberId}")
    public void updateMember(@PathVariable("memberId") Long memberId, @RequestBody Member member) {
        memberService.updateMember(memberId, member);
    }

    @DeleteMapping(path = "{memberId}")
    public void deleteMember(@PathVariable("memberId") Long memberId) {
        memberService.deleteMember(memberId);
    }

    //Role api 

    @PostMapping("/role/addtomember")
    public void addRoleToMember(@RequestBody RoleToMemberForm form) {
        memberService.addRoleToMember(form.getMemberId(), form.getRoleName());
    } 

    @PostMapping("/role/add")
    public void addRole(@RequestBody Role role) {
        memberService.addRole(role);
    }
}

