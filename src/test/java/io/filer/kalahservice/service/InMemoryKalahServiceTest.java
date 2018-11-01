package io.filer.kalahservice.service;

import io.filer.kalah.service.model.GameStarted;
import io.filer.kalah.service.model.MoveResult;
import io.filer.kalahservice.service.impl.InMemoryKalahService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
public class InMemoryKalahServiceTest {

    @TestConfiguration
    static class InMemoryKalahServiceTestContextConfiguration {

        @Bean
        public  InMemoryKalahService inMemoryKalahServiceService() {
            return new InMemoryKalahService();
        }
    }

    @Autowired
    private KalahService kalahService;

    @Test
    public void ensureStartGameReturnsResult_success() {
        GameStarted gameStarted = kalahService.startGame();
        assertThat(gameStarted.getId(), is(notNullValue()));
        assertThat(gameStarted.getUrl(), is(notNullValue()));

        //TODO sort out the actual values to assert
    }

    @Test
    public void ensurMakeMoveReturnsResult_success() {
        GameStarted gameStarted = kalahService.startGame();
        MoveResult moveResult = kalahService.makeMove(gameStarted.getId(), "4");
        assertThat(moveResult.getId(), is(notNullValue()));
        assertThat(moveResult.getUrl(), is(notNullValue()));
        assertThat(moveResult.getState(), is(notNullValue()));

        //TODO sort out the actual values to assert
    }
}
