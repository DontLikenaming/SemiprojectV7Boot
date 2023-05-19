package dontlikenaming.springboot.semiprojectv7;

import dontlikenaming.springboot.semiprojectv7.model.Member;
import dontlikenaming.springboot.semiprojectv7.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("member")
    public void showall(){
        List<Member> items = memberRepository.findAll();

        for(Member item : items) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("member save")
    public void saveMember() {
        Member m = new Member(null, "이름", 010, 1234, 5678, "qwerty", "asdfzxcv", "123-456",  "서울시 가산동", "어딘가", "qwerty@gmail.com", null);

        memberRepository.save(m);
    }

    @Test
    @DisplayName("member update")
    public void updateMember() {
        Member m = new Member(21L, "이름2", 010, 4567, 8901, "asdf", "asdfzxcv", "123-456",  "서울시 가산동", "어딘가", "qwerty@gmail.com", null);

        memberRepository.save(m);
    }

    @Test
    @DisplayName("member delete")
    public void deleteMember() {
        Member m = new Member();
        m.setMbno(21L);

        memberRepository.delete(m);
    }
}
