package io.filer.kalahservice.controller;

import io.filer.kalah.service.model.GameStarted;
import io.filer.kalah.service.model.MoveResult;
import io.filer.kalahservice.service.KalahService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(KalahController.class)
public class KalahControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private KalahService kalahService;

    @Test
    public void whenStartGame_thenReturnGameIdAndUrl()
            throws Exception {

        GameStarted gameStarted = new GameStarted();
        gameStarted.setId("1234");
        gameStarted.setUrl("http://localhost:8080/games/1234");


        given(kalahService.startGame()).willReturn(gameStarted);

        mvc.perform(post("/games")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("1234")))
                .andExpect(jsonPath("$.url", is("http://localhost:8080/games/1234")));
    }

    @Test
    public void whenMakeMoveWithValidGameIdAndPit_thenReturnGameIdAndUrlAndStatus()
            throws Exception {
        MoveResult moveResult = new MoveResult();
        moveResult.setState("1, 2, 3, 4, 5");
        moveResult.setUrl("http://localhost:8080/games/1234");
        moveResult.setId("1234");

        given(kalahService.makeMove("1234", "2")).willReturn(moveResult);

        mvc.perform(put("/games/1234/pits/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1234")))
                .andExpect(jsonPath("$.state", is("1, 2, 3, 4, 5")))
                .andExpect(jsonPath("$.url", is("http://localhost:8080/games/1234")));
    }
}
