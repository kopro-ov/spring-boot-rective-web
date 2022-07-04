package com.greglturnquist.hackingspringboot.reactive.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemUnitTest {

    @Test
    void 아이템_작동해야_하는_항목() {

        Item sampleItem = new Item("TV tray", 19.99, "Alf TV tray");

        //AssertJ를 사용한 값 일치 테스트
        assertThat(sampleItem.getName()).isEqualTo("TV tray");

        Item sampleItem2 = new Item("TV tray", 19.99, "Alf TV tray");


        assertThat(sampleItem).isEqualTo(sampleItem2);

    }

}
