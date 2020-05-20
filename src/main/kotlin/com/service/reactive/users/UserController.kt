package com.service.reactive.users

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(val userRepository: UserRepository) {

    @GetMapping
    fun allUsers() = userRepository.findAll()

    @GetMapping("/{user_id}")
    fun getUserById(@PathVariable("user_id") userId: Long) = userRepository.findById(userId)

    @PostMapping
    fun getUserById(@RequestBody userData: UserData) = userRepository.save(userData)

    @DeleteMapping("/{user_id}")
    fun deleteUserById(@PathVariable("user_id") userId: Long) {
        userRepository.deleteById(userId)
    }
}
