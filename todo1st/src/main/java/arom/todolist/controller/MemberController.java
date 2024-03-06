package arom.todolist.controller;

import arom.todolist.dto.JwtTokenDto;
import arom.todolist.dto.MemberDto;
import arom.todolist.dto.MemberJoinDto;
import arom.todolist.dto.MemberLoginDto;
import arom.todolist.entity.Member;
import arom.todolist.jwt.JwtFilter;
import arom.todolist.jwt.TokenProvider;
import arom.todolist.service.CustomUserDetailsService;
import arom.todolist.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final CustomUserDetailsService userDetailsService;

    @PostMapping("/members/new")
    public ResponseEntity<Member> joinMember(@Valid @RequestBody MemberJoinDto member){
        return ResponseEntity.ok(memberService.join(member));
    }

    @PostMapping("/members/login")
    public ResponseEntity<MemberDto> LoginMember(@Valid @RequestBody MemberLoginDto member){
        return ResponseEntity.ok(memberService.login(member));
    }


    @PostMapping("/members/jwtlogin")
    public ResponseEntity<JwtTokenDto> JwtLoginMember(@Valid @RequestBody MemberLoginDto member){
        JwtTokenDto jwtTokenDto = memberService.jwtLogin(member);
        String accessToken = jwtTokenDto.getAccessToken();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);
        httpHeaders.setLocation(URI.create("/members/info"));
        return  new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
        //return ResponseEntity.ok().headers(httpHeaders).body(jwtTokenDto);
    }

    @GetMapping("/members/info")
    public ResponseEntity<MemberDto> MemberInfo(){
        return ResponseEntity.ok(memberService.getMemberDto());
    }

    @PostMapping("/members/reissue")
    public ResponseEntity<JwtTokenDto> reissue(@RequestBody JwtTokenDto tokenDto){
        return ResponseEntity.ok(memberService.reissue(tokenDto));
    }
}
