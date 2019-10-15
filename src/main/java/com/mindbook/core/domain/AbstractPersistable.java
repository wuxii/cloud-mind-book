package com.mindbook.core.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;

@EqualsAndHashCode()
@Data
public class AbstractPersistable implements Persistable {

    @Include
    @Id
    private Long id;

    @Override
    public boolean isNew() {
        return id == null;
    }

}
