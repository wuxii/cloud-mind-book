package com.harmony.cmb.learning.service;

import com.harmony.cmb.core.Status.MyWordStatus;
import com.harmony.cmb.core.UserInfo;
import com.harmony.cmb.learning.domain.MyWord;
import com.harmony.cmb.learning.repository.MyWordRepository;
import com.harmony.cmb.word.domain.Word;
import com.harmony.cmb.word.service.WordService;
import com.harmony.umbrella.data.repository.QueryableRepository;
import com.harmony.umbrella.data.service.ServiceSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * @author wuxii
 */
@Slf4j
@Service
public class LearningService extends ServiceSupport<MyWord, Long> {

    @Autowired
    private WordService wordService;

    @Autowired
    private MyWordRepository myWordRepository;

    public MyWord learningNewWord(final Word word) {
        UserInfo user = UserInfo.currentUserInfo();
        Word persistWord = wordService.getOrCreateNewWord(word);

        Optional<MyWord> myWordOpt = queryWith()
                .equal("creatorId", user.getUserId())
                .equal("word", persistWord)
                .getSingleResult();

        MyWord myWord = myWordOpt.orElseGet(() -> this.createNewMyWord(persistWord));
        myWord.setStatus(MyWordStatus.READING);
        return this.saveOrUpdate(myWord);
    }

    public List<MyWord> learningWords() {
        return queryWith()
                .equal("text", MyWordStatus.READING)
                .equal("creatorId", UserInfo.currentUserId())
                .desc("createdAt")
                .getListResult();
    }

    public MyWord understoodWord(Long id) {
        MyWord myWord = queryWith()
                .equal("id", id)
                .getSingleResult()
                .orElseThrow(RuntimeException::new);

        if (myWord.getStatus() == MyWordStatus.FINISHED) {
            log.warn("单词已学习完成, 无需重复提交完成学习请求. {}", id);
            return myWord;
        }

        myWord.setStatus(MyWordStatus.FINISHED);
        myWord.setUnderstoodAt(Instant.now());
        return this.saveOrUpdate(myWord);
    }

    private MyWord createNewMyWord(Word word) {
        MyWord myWord = new MyWord();
        myWord.setCreatorId(UserInfo.currentUserId());
        myWord.setStartedAt(Instant.now());
        myWord.setStatus(MyWordStatus.UNREAD);
        myWord.setWord(word);
        return myWord;
    }

    @Override
    protected QueryableRepository<MyWord, Long> getRepository() {
        return myWordRepository;
    }

    @Override
    protected Class<MyWord> getDomainClass() {
        return MyWord.class;
    }

}
