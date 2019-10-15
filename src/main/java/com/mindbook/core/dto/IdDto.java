package com.mindbook.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;
import org.springframework.util.Assert;

/**
 * @author wuxii
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdDto {

    public static IdDto valueOf(Persistable<?> persistable) {
        Assert.notNull(persistable.getId(), "id");
        return new IdDto(persistable.getId());
    }

    private Object id;

}
