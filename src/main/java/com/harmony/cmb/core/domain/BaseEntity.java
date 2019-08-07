package com.harmony.cmb.core.domain;

import com.harmony.cmb.core.jpa.listener.ApplyUserInfoEntityListener;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.Instant;

/**
 * @author wuxii
 */
@Data
@ToString(of = "id")
@MappedSuperclass
@EntityListeners(ApplyUserInfoEntityListener.class)
public class BaseEntity implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(updatable = false)
    protected Instant createdAt;

    @Column(updatable = false)
    protected String creatorName;

    @Column(updatable = false)
    protected Long creatorId;

    @Column(insertable = false)
    protected Instant modifiedAt;

    @Column(insertable = false)
    protected String modifierName;

    @Column(insertable = false)
    protected Long modifierId;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

}
