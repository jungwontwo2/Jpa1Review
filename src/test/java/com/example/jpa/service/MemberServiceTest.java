package com.example.jpa.service;

import com.example.jpa.domain.Member;
import com.example.jpa.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입()throws Exception{
        Member member = new Member();
        member.setName("seo");

        Long savedId = memberService.join(member);

        Assertions.assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복회원예외()throws Exception{
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);
        try {
            memberService.join(member2);
        }catch (IllegalStateException e){
            return;
        }

        Assertions.fail("예외가 발생해야 합니다.");
    }
}
