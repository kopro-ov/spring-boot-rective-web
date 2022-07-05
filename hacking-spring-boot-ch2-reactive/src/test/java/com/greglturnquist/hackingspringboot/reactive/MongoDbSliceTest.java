package com.greglturnquist.hackingspringboot.reactive;

import com.greglturnquist.hackingspringboot.reactive.domain.Item;
import com.greglturnquist.hackingspringboot.reactive.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class MongoDbSliceTest {

    @Autowired
    ItemRepository repository;

    @Test
    void 아이템_저장() {
        Item sampleItem = new Item("name", 19.99, "description");

        repository.save(sampleItem)
                .as(StepVerifier::create)
                .expectNextMatches(item -> {
                    assertThat(item.getId()).isNotNull();
                    assertThat(item.getName()).isEqualTo("name");
                    assertThat(item.getDescription()).isEqualTo("description");
                    assertThat(item.getPrice()).isEqualTo(19.99);

                    return true;
                })
                .verifyComplete();

    }

}
