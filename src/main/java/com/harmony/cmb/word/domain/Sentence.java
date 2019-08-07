package com.harmony.cmb.word.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author wuxii
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sentence {

    @Id
    private Long id;
    private String text;

    @Column(name = "word_id", insertable = false, updatable = false)
    private Long wordId;

    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word;

}
