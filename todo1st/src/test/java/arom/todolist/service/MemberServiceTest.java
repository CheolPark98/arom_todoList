package arom.todolist.service;

import arom.todolist.dto.JwtTokenDto;
import arom.todolist.dto.MemberDto;
import arom.todolist.dto.MemberJoinDto;
import arom.todolist.dto.MemberLoginDto;
import arom.todolist.entity.Member;
import arom.todolist.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.predicate;

@SpringBootTest

class MemberServiceTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;


    @Test
    void login() {

        MemberLoginDto memberLoginDto = new MemberLoginDto("s@sd.s","s");
        MemberJoinDto memberJoinDto = new MemberJoinDto("s@sd.s", "s", "a");
        Member joinedMember = memberService.join(memberJoinDto);
        JwtTokenDto jwtTokenDto = memberService.jwtLogin(memberLoginDto);
        MemberDto login = memberService.login(memberLoginDto);
        System.out.println("jwtTokenDto = " + jwtTokenDto);        //MemberDto loginedMember = memberService.login(memberLoginDto);
    }

    @Test
    void token(){
        Optional<String> s= Optional.ofNullable("s");
        System.out.println("s = " + s.get());

    }
}