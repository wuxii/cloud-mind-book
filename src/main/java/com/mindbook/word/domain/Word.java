package com.mindbook.word.domain;

import com.mindbook.core.domain.AbstractPersistable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;

/**
 * @author wuxii
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("mindbook_word")
public class Word extends AbstractPersistable {

    @NotBlank
    private String text;
    /**
     * 归属语言(zh,en)
     */
    private String language;
    /**
     * 词类型(动词,名词)
     */
    private String category;
    /**
     * <a href="http://www.antimoon.com/how/pronunc-soundsipa.htm">International Phonetic Alphabet</a>
     */
    private String ipa;
    private String remark;

}
