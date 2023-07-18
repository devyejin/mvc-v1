package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemberRepository {

    //static 이니까 싱글톤
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() { // 싱글톤으로 유지하기 위해, 생성자를 private로 이용을 막아야 함

    }

    //저장
    public Member save(Member member) {
        /*
            Long setId(id) {
                return this.id = id; // 이거니까 id값 증가시키기위해 sequence 변수 이용
         */
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    //조회
    public Member findById(Long id) {
        return store.get(id);
    }

    //전체 조회
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //values() : 모든 value 객체를 반환 <- id제외하고 value값만 ArrayList에 담아서 반환!
                                                // store에 저장된 원본을 보호하기 위해 새로운 ArrayList에 반환!
    }

    public void clearStore() {
        store.clear();
    }
}
