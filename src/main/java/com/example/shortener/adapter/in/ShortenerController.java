package com.example.shortener.adapter.in;

import com.example.shortener.adapter.dto.ShortenerRequest;
import com.example.shortener.adapter.dto.ShortenerResponse;
import com.example.shortener.domain.useCase.GenerateShortPathUseCase;
import com.example.shortener.domain.useCase.GetOriginalPathUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/shortener")
public class ShortenerController {


    private final GenerateShortPathUseCase generateShortPath;
    private final GetOriginalPathUseCase originalPathUseCase;

    public ShortenerController(GenerateShortPathUseCase generateShortPath, GetOriginalPathUseCase originalPathUseCase) {
        this.generateShortPath = generateShortPath;
        this.originalPathUseCase = originalPathUseCase;
    }

    @PostMapping
    public ResponseEntity generateShortPath (@RequestBody ShortenerRequest request){

        return ResponseEntity.ok(generateShortPath.execute(request));
    }

    @GetMapping("/{shortPath}")
    public ModelAndView getOriginalPath (@PathVariable("shortPath") @NonNull String shortPath){
        return new ModelAndView("redirect:" +
                originalPathUseCase.execute(shortPath).getOriginalPath());
    }
}
