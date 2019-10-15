package com.mindbook.word.repository;

import com.mindbook.word.domain.Word;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author wuxii
 */
public interface WordRepository extends ReactiveCrudRepository<Word, Long> {
}
