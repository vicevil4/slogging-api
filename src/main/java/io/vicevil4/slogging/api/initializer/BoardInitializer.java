package io.vicevil4.slogging.api.initializer;

import io.vicevil4.slogging.api.module.model.BoardModel;
import io.vicevil4.slogging.api.module.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardInitializer implements ApplicationRunner {

    private final BoardRepository boardRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        boardRepository.save(BoardModel.builder().boardName("FreeBoard").build());
        boardRepository.save(BoardModel.builder().boardName("Notice").build());
    }
}
