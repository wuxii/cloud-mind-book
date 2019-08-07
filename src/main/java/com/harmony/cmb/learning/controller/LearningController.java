package com.harmony.cmb.learning.controller;

import com.harmony.cmb.core.dto.IdDto;
import com.harmony.cmb.learning.domain.MyWord;
import com.harmony.cmb.learning.service.LearningService;
import com.harmony.cmb.word.domain.Word;
import com.harmony.umbrella.web.Response;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author wuxii
 */
@RestController
@RequestMapping("/learning")
public class LearningController {

    @Autowired
    private LearningService learningService;

    // 学习新词
    @PostMapping()
    public Response<IdDto> newWord(@Valid @RequestBody Word word) {
        MyWord myWord = learningService.learningNewWord(word);
        return Response.ok(IdDto.valueOf(myWord));
    }

    // 理解完成
    @PutMapping("/understood/{id}")
    public Response<Void> understood(@PathVariable("id") Long id) {
        learningService.understoodWord(id);
        return Response.ok();
    }

    // 当前学习中的词
    @GetMapping("/current")
    public Response<Object> current() {
        return Response.ok(learningService.learningWords());
    }

    // 随机推荐新词
    @GetMapping("/recommend")
    public Response<Object> recommend(@RequestParam(defaultValue = "10") @Range(min = 1, max = 100) int size) {
        return Response.error("uncompleted");
    }

}
