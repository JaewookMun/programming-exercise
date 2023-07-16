package example.jpaassociation.entity.unique;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "member_guid_uk", columnNames = "guid"),
                @UniqueConstraint(name = "member_login_id_uk", columnNames = "loginId")
        }
)
@Getter
public class MemberUnique {

    @Id @GeneratedValue
    private Long id;

    private String guid;
    private String loginId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_guid", referencedColumnName = "guid")
    private DeptUnique dept;

    public MemberUnique(String guid, String loginId, DeptUnique dept) {
        this.guid = guid;
        this.loginId = loginId;
        this.dept = dept;
    }

    public void changeDept(DeptUnique dept) {
        Assert.notNull(dept, "dept should be not null");
        this.dept = dept;
    }
}
