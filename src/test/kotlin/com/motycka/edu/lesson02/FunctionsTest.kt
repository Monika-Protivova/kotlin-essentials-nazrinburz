package com.motycka.edu.lesson02

import com.motycka.edu.captureStdout
import com.motycka.edu.getClass
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.doubles.shouldBeExactly
import io.kotest.matchers.maps.shouldBeEmpty
import io.kotest.matchers.shouldBe

class FunctionsTest : StringSpec({

    val functionsClass = getClass("com.motycka.edu.lesson02.FunctionsKt")
    val coffeeOrdersField = functionsClass.getDeclaredField("coffeeOrders").apply { isAccessible = true }

    fun getCoffeeOrders(): MutableMap<Int, List<String>> {
        return coffeeOrdersField.get(null) as MutableMap<Int, List<String>>
    }

    fun placerOrder(items: List<String>): Int {
        return functionsClass.getDeclaredMethod("placerOrder", List::class.java)
            .apply { isAccessible = true }
            .invoke(null, items) as Int
    }

    fun payOrder(orderId: Int): Double {
        try {
            return functionsClass.getDeclaredMethod("payOrder", Int::class.java)
                .apply { isAccessible = true }
                .invoke(null, orderId) as Double
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }

    fun completeOrder(orderId: Int) {
        try {
            functionsClass.getDeclaredMethod("completeOrder", Int::class.java)
                .apply { isAccessible = true }
                .invoke(null, orderId)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }

    fun processOrder(items: List<String>, payment: Double): Double {
        try {
            return functionsClass.getDeclaredMethod("processOrder", List::class.java, Double::class.java)
                .apply { isAccessible = true }
                .invoke(null, items, payment) as Double
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }

    beforeTest {
        getCoffeeOrders().clear()
    }

    "placerOrder should add items to coffeeOrders and return orderId" {
        val items = listOf(ESPRESSO, CAPPUCCINO)
        val orderId = placerOrder(items)

        orderId shouldBe 0
        getCoffeeOrders()[orderId] shouldBe items
    }

    "payOrder should calculate correct total for a single item" {
        val orderId = placerOrder(listOf(ESPRESSO))
        val total = payOrder(orderId)

        total shouldBeExactly ESPRESSO_PRICE
    }

    "payOrder should calculate correct total for multiple items" {
        val orderId = placerOrder(listOf(ESPRESSO, CAPPUCCINO))
        val total = payOrder(orderId)

        total shouldBeExactly (ESPRESSO_PRICE + CAPPUCCINO_PRICE)
    }

    "payOrder should apply discount for 3 or more items" {
        val orderId = placerOrder(listOf(ESPRESSO, CAPPUCCINO, AMERICANO, FLAT_WHITE))
        val total = payOrder(orderId)

        // Discount should be the price of the cheapest item (AMERICANO)
        total shouldBeExactly (ESPRESSO_PRICE + CAPPUCCINO_PRICE + FLAT_WHITE_PRICE)
    }

    "payOrder should throw error for non-existent order" {
        val exception = shouldThrow<IllegalStateException> {
            payOrder(999)
        }

        exception.message shouldBe "Order ID 999 not found."
    }

    "completeOrder should remove the order from coffeeOrders" {
        val orderId = placerOrder(listOf(ESPRESSO))

        getCoffeeOrders().size shouldBe 1

        completeOrder(orderId)

        getCoffeeOrders().size shouldBe 0
    }

    "completeOrder should throw error for non-existent order" {
        val exception = shouldThrow<IllegalStateException> {
            completeOrder(999)
        }

        exception.message shouldBe "Order ID 999 not found."
    }

    "processOrder should calculate correct change" {
        val items = listOf(ESPRESSO, CAPPUCCINO)
        val payment = 10.0
        val expectedTotal = ESPRESSO_PRICE + CAPPUCCINO_PRICE
        val expectedChange = payment - expectedTotal

        val change = processOrder(items, payment)

        change shouldBeExactly expectedChange
        getCoffeeOrders().shouldBeEmpty()
    }

    "processOrder should throw error for insufficient payment" {
        val items = listOf(ESPRESSO, CAPPUCCINO, LATTE)
        val payment = 5.0
        val expectedTotal = ESPRESSO_PRICE + CAPPUCCINO_PRICE + LATTE_PRICE

        val exception = shouldThrow<IllegalStateException> {
            processOrder(items, payment)
        }

        exception.message shouldBe "Insufficient payment. Total to pay is $expectedTotal, but received $payment."
    }

    "processOrder should print payment information" {
        val items = listOf(ESPRESSO, CAPPUCCINO)
        val payment = 10.0
        val expectedTotal = ESPRESSO_PRICE + CAPPUCCINO_PRICE
        val expectedChange = payment - expectedTotal

        val output = captureStdout {
            processOrder(items, payment)
        }

        output shouldBe "Payment successful. Total paid: $payment, Total to pay: $expectedTotal, change: $expectedChange"
    }
})
