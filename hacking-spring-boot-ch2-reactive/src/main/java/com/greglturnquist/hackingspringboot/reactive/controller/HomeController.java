package com.greglturnquist.hackingspringboot.reactive.controller;

import com.greglturnquist.hackingspringboot.reactive.domain.Cart;
import com.greglturnquist.hackingspringboot.reactive.repository.CartRepository;
import com.greglturnquist.hackingspringboot.reactive.repository.ItemRepository;
import com.greglturnquist.hackingspringboot.reactive.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    private final CartService cartService;


    @GetMapping
    Mono<Rendering> home() {
        return Mono.just(Rendering.view("home.html")
                .modelAttribute("items", this.itemRepository.findAll())
                .modelAttribute("cart", this.cartRepository.findById("My Cart")
                        .defaultIfEmpty(new Cart("My Cart"))).build());
    }

    @PostMapping("/add/{id}")
    Mono<String> add(@PathVariable String id) {
        return this.cartService.addToCart("My Cart", id)
                .thenReturn("redirect:/");
    }

}
