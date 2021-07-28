package com.kwchaiwat.dev.springbootkotlin.controller

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.kwchaiwat.dev.springbootkotlin.model.Bank
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.*


@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest  @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper

) {
    val baseUrl = "/api/banks"

    @Nested
    @DisplayName("GET /api/banks")
    @TestInstance(PER_CLASS)
    inner class GetBanks {
        @Test
        fun `should return all banks`() {
            // when/ Then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status {isOk()}
                    content { contentType(APPLICATION_JSON)}
                    jsonPath("$[0].accountNumber") { value("AA-12311") }
                }
        }
    }

    @Nested
    @DisplayName("GET /api/banks/{accountNumber}")
    @TestInstance(PER_CLASS)
    inner class GetBank {
        @Test
        fun `should return the bank with the given account number`() {
            // given
            val accountNumber =  "AB-56563"

            // when/ Then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status {isOk()}
                    content { contentType(APPLICATION_JSON)}
                    jsonPath("$.trust") { value(312.22) }
                    jsonPath("$.transactionFee") { value(40) }
                }

        }

        @Test
        fun `should return Not Found if the account number does not exist`() {
            // given
            val accountNumber = "does_not_exist"

            // when/ Then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }

        }
    }

    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(PER_CLASS)
    inner class PostNewBank {
        @Test
        fun `should add the new bank`() {
            // given
            val newBank = Bank().apply {
                this.accountNumber = "abc123"
                this.trust = 31.41
                this.transactionFee = 2
            }

            // when
            val performPost = mockMvc.post(baseUrl){
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(APPLICATION_JSON)
                        jsonPath("$.accountNumber") { value("abc123") }
                        jsonPath("$.trust") { value(31.41) }
                        jsonPath("$.transactionFee") { value(2) }
                    }
                }

            mockMvc.get("$baseUrl/${newBank.accountNumber}")
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(APPLICATION_JSON)
                        jsonPath("$.accountNumber") { value("abc123") }
                        jsonPath("$.trust") { value(31.41) }
                        jsonPath("$.transactionFee") { value(2) }
                    }
                }

        }
        
        @Test
        fun `should return BAD REQUEST if bank with given account number already exists`() {
            // given
            val invalidBank = Bank().apply {
                this.accountNumber = "AA-12311"
                this.trust = 1922.33
                this.transactionFee = 30
            }

            // when
            val performPost = mockMvc.post(baseUrl){
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }

            // Then
            performPost
                .andDo { print() }
                .andExpect { status { isBadRequest() } }
            
        }
    }

    @Nested
    @DisplayName("PATCH /api/banks")
    @TestInstance(PER_CLASS)
    inner class PatchExistingBank {
        @Test
        fun `should update an existing bank`() {
            // given
            val updatedBank = Bank().apply {
                this.accountNumber = "AC-91923"
                this.trust = 9999.99
                this.transactionFee = 99
            }
            // when
            val performPatchRequest = mockMvc.patch(baseUrl){
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedBank)
            }

            // Then
            performPatchRequest
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(APPLICATION_JSON)
                        jsonPath("$.accountNumber") { value("AC-91923") }
                        jsonPath("$.trust") { value(9999.99) }
                        jsonPath("$.transactionFee") { value(99) }
                    }
                }

            mockMvc.get("$baseUrl/${updatedBank.accountNumber}")
                .andExpect { content { contentType(APPLICATION_JSON)
                    jsonPath("$.accountNumber") { value("AC-91923") }
                    jsonPath("$.trust") { value(9999.99) }
                    jsonPath("$.transactionFee") { value(99) }
                } }
        }
        
        @Test
        fun `should return BAD REQUEST if no bank with given account number exists`() {
            // given
            val invalidBank = Bank().apply {
                this.accountNumber = "does_not_exist"
                this.trust = 1.0
                this.transactionFee = 1
            }
            
            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }
            
            // Then
            performPatchRequest
                .andDo { print() }
                .andExpect { status { isNotFound() } }
            
        }

    }

    @Nested
    @DisplayName("DELETE /api/banks")
    @TestInstance(PER_CLASS)
    inner class DeleteExistingBank {
        @Test
        fun `should delete the bank with the given account number`() {
            // given
            val accountNumber = "abc123"

            // when/ then
            mockMvc.delete("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect { status { isNoContent() } }

            mockMvc.get("$baseUrl/$accountNumber")
                .andExpect { status { isNotFound() } }
        }

        @Test
        fun `should return NOT FOUND if no bank with given account number exitsts`() {
            // given
            val invalidAccountNumber = "does_not_exist"

            // when/ then
            mockMvc.delete("$baseUrl/$invalidAccountNumber")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
        
    }

}