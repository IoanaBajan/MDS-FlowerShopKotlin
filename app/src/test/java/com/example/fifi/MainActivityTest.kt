package com.example.fifi

import com.example.fifi.model.Client
import org.junit.Test

import org.junit.Assert.*

class MainActivityTest {
    var client : Client =
        Client("0", "", "", "nume", "parola", "email")

    @Test
    fun getUsername() {
        assertEquals(client.username, "nume")
    }

    @Test
    fun getPassword() {
        assertEquals(client.password, "parola")
    }

    @Test
    fun setUsername() {
        assertNotNull(client.username)
    }

    @Test
    fun setPassword() {
        assertTrue(client.password.length >= 4)
    }
}