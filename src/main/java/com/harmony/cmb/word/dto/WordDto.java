package com.harmony.cmb.word.dto;

import lombok.Data;

import java.util.List;

/**
 * @author wuxii
 */
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

}
