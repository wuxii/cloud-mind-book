package com.harmony.cmb.word.service;

import com.harmony.cmb.word.domain.Word;
import com.harmony.cmb.word.repository.WordRepository;
import com.harmony.umbrella.data.repository.QueryableRepository;
import com.harmony.umbrella.data.service.ServiceSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author wuxii
 */
@Service
public class WordService extends ServiceSupport<Word, Long> {

    @Autowired
    private WordRepository wordRepository;

    public Word addNewWord(Word word) {
        String wordText = word.getText().trim();
        word.setId(null);
        word.setText(wordText);
        word.setLanguage(analyzeLanguage(wordText));
        return saveOrUpdate(word);
    }

    public Word getOrCreateNewWord(Word word) {
        String wordText = word.getText().trim();
        return queryWith()
                .equal("language", wordLanguage(word))
                .equal("text", wordText)
                .getSingleResult()
                .orElseGet(() -> addNewWord(word));
    }

    private String wordLanguage(Word word) {
        return StringUtils.hasText(word.getLanguage()) ? word.getLanguage() : analyzeLanguage(word.getText().trim());
    }

    protected String analyzeLanguage(String wordText) {
        return Character.isAlphabetic(wordText.charAt(0)) ? "en" : "cn";
    }

    @Override
    protected QueryableRepository<Word, Long> getRepository() {
        return wordRepository;
    }

    @Override
    protected Class<Word> getDomainClass() {
        return Word.class;
    }

}
