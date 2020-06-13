package com.example.fifi

import com.example.fifi.model.Client
import org.junit.Test

import org.junit.Assert.*

class SignUpActivityTest {
    var client : Client =
        Client(
            "1",
            "Popescu",
            "Maria",
            "maria",
            "parola",
            "mariap@gmail.com"
        )

    @Test
    fun getIdUsernameR() {
        assertNotNull(client.username)
        assertEquals(client.username, "maria")
    }

    @Test
    fun getIdPasswordR() {
        assertTrue(client.password.length >= 4)
        assertEquals(client.password, "parola")
    }

    @Test
    fun getIdEmail() {
        assertNotNull(client.email)
        assertEquals(client.email, "mariap@gmail.com")
        assertTrue(client.email.contains("@"))
    }

    @Test
    fun getIdLastName() {
        assertNotNull(client.lastName)
        assertEquals(client.lastName, "Maria")
    }

    @Test
    fun getIdFirstName() {
        assertNotNull(client.firstName)
        assertEquals(client.firstName, "Popescu")

    }

    @Test
    fun getIdRegister() {
        assertNotNull(client.id)
        assertEquals(client.id, "1")
    }

}