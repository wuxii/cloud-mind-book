package com.mindbook.word.service;

import com.mindbook.word.domain.Word;
import com.mindbook.word.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author wuxii
 */
@RequiredArgsConstructor
@Service
public class WordService {

    private final WordRepository wordRepository;

    public Mono<Word> addNewWord(Word word) {
        String wordText = word.getText().trim();
        word.setId(null);
        word.setText(wordText);
        word.setLanguage(analyzeLanguage(wordText));
        return wordRepository.save(word);
    }

    public Mono<Word> findById(Long id) {
        return wordRepository.findById(id).doOnNext(System.out::println);
    }

    public Mono<Void> deleteById(Long id) {
        return wordRepository.deleteById(id);
    }

    protected String analyzeLanguage(String wordText) {
        return Character.isAlphabetic(wordText.charAt(0)) ? "en" : "cn";
    }

}
