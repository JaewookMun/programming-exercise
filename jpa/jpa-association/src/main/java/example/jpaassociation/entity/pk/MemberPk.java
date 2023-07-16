package example.jpaassociation.entity.pk;

import example.jpaassociation.entity.unique.DeptUnique;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "memberpk_guid_uk", columnNames = "guid"),
                @UniqueConstraint(name = "memberpk_login_id_uk", columnNames = "loginId")
        }
)
@Getter
public class MemberPk {

    @Id @GeneratedValue
    private Long id;

    private String guid;
    private String loginId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_guid")
    private DeptPk dept;

    public MemberPk(String guid, String loginId, DeptPk dept) {
        this.guid = guid;
        this.loginId = loginId;
        this.dept = dept;
    }

    public void changeDept(DeptPk dept) {
        Assert.notNull(dept, "dept should be not null");
        this.dept = dept;
    }
}
