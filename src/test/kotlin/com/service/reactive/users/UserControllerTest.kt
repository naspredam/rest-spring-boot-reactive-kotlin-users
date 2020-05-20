package com.service.reactive.users

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@ExtendWith(SpringExtension::class)
@WebFluxTest(controllers = [UserController::class])
internal class UserControllerTest {

    @Autowired
    private val webClient: WebTestClient? = null

    @MockBean
    private val userRepository: UserRepository? = null

    @Test
    fun shouldReturnAllUsers_fromRepository() {
        val userData = UserData(10L, "Bilbo", "Baggins", "+44 9999-99999")
        val userDataMono = Mono.just(userData)
        Mockito.`when`(userRepository!!.findAll())
                .thenReturn(Flux.mergeSequential(userDataMono))
        webClient!!.get()
                .uri("/users")
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("length()").isEqualTo(1)
                .jsonPath("[0].id").isEqualTo("10")
                .jsonPath("[0].first_name").isEqualTo("Bilbo")
                .jsonPath("[0].last_name").isEqualTo("Baggins")
                .jsonPath("[0].phone").isEqualTo("+44 9999-99999")
    }

    @Test
    fun shouldReturnSpecificUserData_fromRepositoryById() {
        val userData = UserData(10L, "Bilbo", "Baggins", "+44 9999-99999")
        Mockito.`when`(userRepository!!.findById(10L))
                .thenReturn(Mono.just(userData))
        webClient!!.get()
                .uri("/users/10")
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("id").isEqualTo("10")
                .jsonPath("first_name").isEqualTo("Bilbo")
                .jsonPath("last_name").isEqualTo("Baggins")
                .jsonPath("phone").isEqualTo("+44 9999-99999")
    }

    @Test
    fun shouldCreateNewUserData_ToTheRepository() {
        val requestUserData = UserData(null,"Frodo", "Baggins", "+44 7777-7777")
        val savedUserData = UserData(11L, requestUserData.firstName, requestUserData.lastName, requestUserData.phone)
        Mockito.`when`(userRepository!!.save(requestUserData))
                .thenReturn(Mono.just(savedUserData))
        webClient!!.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestUserData))
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("id").isEqualTo("11")
                .jsonPath("first_name").isEqualTo("Frodo")
                .jsonPath("last_name").isEqualTo("Baggins")
                .jsonPath("phone").isEqualTo("+44 7777-7777")
    }

    @Test
    fun shouldReturnDeleteSpecificUser_fromRepositoryById() {
        webClient!!.delete()
                .uri("/users/10")
                .exchange()
                .expectStatus().isOk

        Mockito.verify(userRepository)?.deleteById(10L)
    }
}