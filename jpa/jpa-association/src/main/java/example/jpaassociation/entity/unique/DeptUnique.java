package example.jpaassociation.entity.unique;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
public class DeptUnique implements Serializable {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String guid;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_guid", referencedColumnName = "guid")
    private DeptUnique parent;

    public DeptUnique(String guid, String name, DeptUnique parent) {
        this.guid = guid;
        this.name = name;
        this.parent = parent;
    }
}
