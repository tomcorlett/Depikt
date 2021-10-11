package corlett.depikt.dev.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corlett.depikt.dev.model.Description;
import corlett.depikt.dev.model.Favorite;
import corlett.depikt.dev.model.Member;
import corlett.depikt.dev.model.MemberToDescriptionForm;
import corlett.depikt.dev.service.DescriptionServiceImpl;
import corlett.depikt.dev.service.MemberService;
import corlett.depikt.dev.service.MemberServiceImpl;


@RestController
@RequestMapping(path = "api/v1/description")
public class DescriptionController {
    private final DescriptionServiceImpl descriptionService;
    private final MemberServiceImpl memberService;

    @Autowired
    public DescriptionController(DescriptionServiceImpl descriptionService, MemberServiceImpl memberService) {
        this.descriptionService = descriptionService;
        this.memberService = memberService;
    }

    @GetMapping(path = "{descriptionId}")
    public Description getDescription(@PathVariable("descriptionId") Long descriptionId) {
        return descriptionService.getDescription(descriptionId);
    }

    @PostMapping
    public void addDescription(@RequestBody Description description) {
        descriptionService.addDescription(description);
    }    

    //Favorite requests
    @PutMapping("/favorite/add")
    public void addFavoriteToDescription(@RequestBody MemberToDescriptionForm form) {
        Long memberId = form.getMemberId();
        Long descriptionId = form.getDescriptionId();
        Member member = memberService.getMember(memberId);
        Description description = descriptionService.getDescription(descriptionId);
        Favorite favorite = new Favorite(member, description, LocalDateTime.now());
        Set<Favorite> likedDescriptions = member.getFavorites();
        if (!likedDescriptions.contains(favorite)) {
            likedDescriptions.add(favorite);
        }
        //member.setFavorites(likedDescriptions);
        description.setFavorites(likedDescriptions);
        memberService.updateMember(memberId, member);
    }

    @DeleteMapping("/favorite/delete")
    public void removeFavoriteFromDescription(@RequestBody MemberToDescriptionForm form) {
        Long memberId = form.getMemberId();
        Long descriptionId = form.getDescriptionId();
        Member member = memberService.getMember(memberId);
        Description description = descriptionService.getDescription(descriptionId);
        Favorite favorite = new Favorite(member, description, LocalDateTime.now());
        Set<Favorite> likedDescriptions = member.getFavorites();
        if (likedDescriptions.contains(favorite)) {
            likedDescriptions.remove(favorite);
        }
        //member.setFavorites(likedDescriptions);
        description.setFavorites(likedDescriptions);
        memberService.updateMember(memberId, member);
    }
}
