package com.service.reactive.users

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.reactive.ReactiveCrudRepository

@Table("users")
data class UserData (@Id val id: Long?, val firstName: String, val lastName: String, val phone: String)

interface UserRepository: ReactiveCrudRepository<UserData, Long>