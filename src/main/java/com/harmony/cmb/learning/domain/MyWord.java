package com.harmony.cmb.learning.domain;

import com.harmony.cmb.core.Status.MyWordStatus;
import com.harmony.cmb.core.domain.BaseEntity;
import com.harmony.cmb.word.domain.Word;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

/**
 * @author wuxii
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MyWord extends BaseEntity {

    /**
     * 学习状态(10.learningWords, 20.reading, 30.finish)
     */
    @Column(length = 20)
    private MyWordStatus status;

    private Instant startedAt;

    private Instant understoodAt;

    @Column(name = "word_id", insertable = false, updatable = false)
    private Long wordId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "word_id")
    private Word word;

}
