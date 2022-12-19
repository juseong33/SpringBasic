package Hello.HelloSpring.repository;

import Hello.HelloSpring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository mRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        mRepository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("Spring");

        mRepository.save(member);
        mRepository.findById(member.getId());

        Member result = mRepository.findById(member.getId()).get();
        //System.out.println("Result = " + (result==member));
        //Assertions.assertEquals(member, result);
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");
        mRepository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        mRepository.save(member2);

        Member result = mRepository.findByName("Spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("Spring1");
        mRepository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        mRepository.save(member2);

        List<Member> result = mRepository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
