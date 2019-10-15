package com.mindbook.word.domain;

import com.mindbook.core.domain.AbstractPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Table("mindbook_paraphrase")
public class Paraphrase extends AbstractPersistable {

    private Long wordId;
    private String paraphrase;

}
