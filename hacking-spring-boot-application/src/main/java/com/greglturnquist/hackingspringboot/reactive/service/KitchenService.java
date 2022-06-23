package com.greglturnquist.hackingspringboot.reactive.service;

import com.greglturnquist.hackingspringboot.reactive.domain.Dish;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class KitchenService {

    /**
     * 메뉴
     */
    private List<Dish> menu = Arrays.asList(
            new Dish("뉴욕 피자"),
            new Dish("시카고 피자"),
            new Dish("고르곤졸라 피자"),
            new Dish("파인애플 피자")
    );

    /**
     * 요리 스트럼 생성
     * @return
     */
    public Flux<Dish> getDishes() {
        return Flux.<Dish> generate(sink -> sink.next(ranDomDish())).delayElements(Duration.ofMillis(250));
    }

    /**
     * 요리 무작위 선택
     * @return
     */
    private Dish ranDomDish() {
        return menu.get(new Random().nextInt(menu.size()));
    }

}
