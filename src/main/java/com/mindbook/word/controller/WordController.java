package com.mindbook.word.controller;

import com.mindbook.core.dto.IdDto;
import com.mindbook.word.domain.Word;
import com.mindbook.word.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author wuxii
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/word")
public class WordController {

    private final WordService wordService;

    @GetMapping("/i/{id}")
    public Mono<Word> info(@NotNull @PathVariable("id") Long id) {
        return wordService.findById(id);
    }

    @PostMapping("/add")
    public Mono<IdDto> add(@Valid @RequestBody Word word) {
        return wordService.addNewWord(word).map(IdDto::valueOf);
    }

    @DeleteMapping("/del/{id}")
    public Mono<Void> delete(@NotNull @PathVariable(name = "id") Long id) {
        return wordService.deleteById(id);
    }

}
