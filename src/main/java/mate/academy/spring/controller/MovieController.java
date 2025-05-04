package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.dto.MovieRequestDto;
import mate.academy.spring.model.dto.MovieResponseDto;
import mate.academy.spring.service.MovieService;
import mate.academy.spring.service.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieService movieService,
                           MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @GetMapping
    public List<MovieResponseDto> get() {
        return movieService.getAll().stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public MovieResponseDto create(@RequestBody MovieRequestDto movieRequestDto) {
        return movieMapper.toDto(movieService.add(movieMapper.toModel(movieRequestDto)));
    }
}
