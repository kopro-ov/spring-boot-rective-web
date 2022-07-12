package com.greglturnquist.hackingspringboot.reactive.controller;

import com.greglturnquist.hackingspringboot.reactive.domain.Item;
import com.greglturnquist.hackingspringboot.reactive.repository.ItemRepository;
import com.greglturnquist.hackingspringboot.reactive.service.InventoryService;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@DisplayName("문서를 자동으로 생성하는 첫 번째 테스트 케이스")
@WebFluxTest(controllers = ApiItemController.class)
@AutoConfigureRestDocs
public class ApiItemControllerDocumentationTest {


    private final WebTestClient webTestClient;

    public ApiItemControllerDocumentationTest(@Autowired WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    @MockBean
    InventoryService service;
    @MockBean
    ItemRepository repository;

    @DisplayName("[api] 새 객체 추가")
    @Test
    void findingAllItems() {
        // Given

        // When
        when(repository.save(any())).thenReturn(
                Mono.just(new Item("1", "Alf alarm clock", 19.99, "nothing important")));

        // Then
        this.webTestClient.post().uri("/api/items")
                .bodyValue(new Item("Alf alarm clock", 19.99, "nothing important"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .consumeWith(document("post-new-item", preprocessResponse(prettyPrint())));

    }

}
