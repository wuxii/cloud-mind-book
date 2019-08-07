package com.harmony.cmb.word.domain;

import com.harmony.cmb.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author wuxii
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Word extends BaseEntity {

    @NotBlank
    private String text;
    /**
     * 归属语言(cn,en)
     */
    private String language;
    /**
     * 词类型(动词,名词)
     */
    private String category;

    private String paraphrase;
    /**
     * <a href="http://www.antimoon.com/how/pronunc-soundsipa.htm">International Phonetic Alphabet</a>
     */
    private String ipa;
    private String remark;

    @OneToMany(mappedBy = "word", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Sentence> sentences;

}
