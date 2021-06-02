package wooteco.subway.member.domain;

public class LoginMember {

    private final Long id;
    private final String email;
    private final Integer age;

    private LoginMember(Long id, String email, Integer age) {
        this.id = id;
        this.email = email;
        this.age = age;
    }

    public static LoginMember from(Member member) {
        return new LoginMember(member.getId(), member.getEmail(), member.getAge());
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }
}
