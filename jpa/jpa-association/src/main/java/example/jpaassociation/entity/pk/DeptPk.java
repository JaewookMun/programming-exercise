package example.jpaassociation.entity.pk;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
public class DeptPk implements Persistable<String> {

    @Id
    private String guid;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_guid")
    private DeptPk parent;

    @Transient
    private boolean isCreated;

    @Override
    public String getId() {
        return guid;
    }

    @Override
    public boolean isNew() {
        return isCreated;
    }

    public DeptPk(String guid, String name, DeptPk parent) {
        this.guid = guid;
        this.name = name;
        this.parent = parent;
        this.isCreated = true;
    }
}
