package com.mindbook.word.service;

import com.mindbook.word.domain.Word;
import com.mindbook.word.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.query.Criteria;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author wuxii
 */
@RequiredArgsConstructor
@Service
public class WordService {

    private final WordRepository wordRepository;
    private final DatabaseClient databaseClient;

    public Mono<Word> addNewWord(Word word) {
        String wordText = word.getText().trim();
        word.setId(null);
        word.setText(wordText);
        word.setLanguage(analyzeLanguage(wordText));
        return wordRepository.save(word);
    }

    public Flux<Word> recommend() {
        Criteria criteria = Criteria
                .where("category").is("n")
                .and("text").like("%a%");
        return databaseClient
                .select()
                .from(Word.class)
                .matching(criteria)
                .fetch()
                .all();
    }

    public Mono<Word> findById(Long id) {
        return wordRepository.findById(id);
    }

    public Mono<Void> deleteById(Long id) {
        return wordRepository.deleteById(id);
    }

    protected String analyzeLanguage(String wordText) {
        return Character.isAlphabetic(wordText.charAt(0)) ? "en" : "cn";
    }

}
