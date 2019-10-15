package com.mindbook.word.domain;

import com.mindbook.core.domain.AbstractPersistable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author wuxii
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("mindbook_sentence")
public class Sentence extends AbstractPersistable {

    private String sentence;
    private Long wordId;

}
