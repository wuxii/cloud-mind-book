package com.harmony.cmb.word.controller;

import com.harmony.cmb.core.dto.IdDto;
import com.harmony.cmb.word.domain.Word;
import com.harmony.cmb.word.dto.WordDto;
import com.harmony.cmb.word.service.WordService;
import com.harmony.umbrella.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author wuxii
 */
@RestController
@RequestMapping("/word")
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping("/i/{id}")
    public Response<WordDto> info(@NotNull @PathVariable("id") Long id) {
        // Optional<Word> word = wordService.findById(id);
        // return Response.ok(word.orElse(null));
        return Response.ok();
    }

    @PostMapping("/add")
    public Response<IdDto> add(@Valid @RequestBody Word word) {
        Word newWord = wordService.addNewWord(word);
        return Response.ok(IdDto.valueOf(newWord));
    }

    @DeleteMapping("/del/{id}")
    public Response<Void> delete(@NotNull @PathVariable(name = "id") Long id) {
        wordService.deleteById(id);
        return Response.ok();
    }

}
