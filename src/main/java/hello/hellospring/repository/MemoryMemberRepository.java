package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements  MemberRepository {


    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 시퀀스는 0,1,2 값 같은거 생성해주는거

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id));  //Null을 반환할것같으면 optional을 붙여줌
    }

    @Override
    public Optional<Member> findByName(String name) {

        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findALl() {

        return new ArrayList<>(store.values());
    }
}
