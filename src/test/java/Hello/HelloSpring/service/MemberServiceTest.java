package Hello.HelloSpring.service;

import Hello.HelloSpring.domain.Member;
import Hello.HelloSpring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemoryMemberRepository mRepository;
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        mRepository = new MemoryMemberRepository();
        memberService = new MemberService(mRepository);
    }
    @AfterEach
    public void afterEach() {
        mRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("Hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        //when
        memberService.join(member1);
        IllegalStateException e = org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*      try {
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }

 */
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}