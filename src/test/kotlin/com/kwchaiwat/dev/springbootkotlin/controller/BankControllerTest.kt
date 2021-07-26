package com.kwchaiwat.dev.springbootkotlin.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.kwchaiwat.dev.springbootkotlin.model.Bank
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.springframework.beans.factory.annotation.Autowired
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
                    jsonPath("$[0].accountNumber") { value("32132") }
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
            val accountNumber =  323114

            // when/ Then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status {isOk()}
                    content { contentType(APPLICATION_JSON)}
                    jsonPath("$.trust") { value(1.22) }
                    jsonPath("$.transactionFee") { value(232) }
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
            val newBank = Bank("acc123", 31.415, 2)

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
                        json(objectMapper.writeValueAsString(newBank))
                    }
                }

            mockMvc.get("$baseUrl/${newBank.accountNumber}")
                .andExpect { content { json(objectMapper.writeValueAsString(newBank)) } }

        }
        
        @Test
        fun `should return BAD REQUEST if bank with given account number already exists`() {
            // given
            val invalidbank = Bank("32132", 3.14, 11)

            // when
            val performPost = mockMvc.post(baseUrl){
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidbank)
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
            val updatedBank = Bank("321", 1.0, 1)
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
                        json(objectMapper.writeValueAsString(updatedBank))
                    }
                }

            mockMvc.get("$baseUrl/${updatedBank.accountNumber}")
                .andExpect { content { json(objectMapper.writeValueAsString(updatedBank)) } }
        }
        
        @Test
        fun `should return BAD REQUEST if no bank with given account number exists`() {
            // given
            val invalidBank = Bank("does_not_exist", 1.0, 1)
            
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
            val accountNumber = 321

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