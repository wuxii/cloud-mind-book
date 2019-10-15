package com.mindbook.word.dto;

import com.mindbook.word.domain.Word;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wuxii
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WordDto {

    private Long wordId;

    private String text;
    private String language;
    private String category;
    private String paraphrase;
    private String ipa;
    private String remark;

    private Integer learningCount;
    private Integer understoodCount;

    private List<SentenceDto> sentences;

    public WordDto(Word word) {

    }

}
