package com.motycka.edu.lesson02

val coffeeOrders = mutableMapOf<Int, List<String>>()
var lastOrderId = 0

fun main() {
    // You can write code here to try the functions
    processOrder(listOf(ESPRESSO, CAPPUCCINO, CAPPUCCINO, AMERICANO), 20.0)
    processOrder(listOf(ESPRESSO, FLAT_WHITE, AMERICANO), 10.0)
    //processOrder(listOf(ESPRESSO, ESPRESSO, DOUBLE_ESPRESSO), 5.0) // will fail due to insufficient payment
}

/* Implement the functions below */
//placerOrder in order to pass test

fun processOrder(items: List<String>, payment: Double): Double {
    val orderId = placerOrder(items)
    val totalToPay = payOrder(orderId)

    if (payment < totalToPay) {
        throw IllegalStateException("Insufficient payment. Total to pay is $totalToPay, but received $payment.")
    }
    val change = payment-totalToPay
    println("Payment successful. Total paid: $payment, Total to pay: $totalToPay, change: $change")
    completeOrder(orderId)

    return change
}


fun placerOrder(items: List<String>): Int {
    val newOrderId = lastOrderId
    coffeeOrders[newOrderId] = items
    lastOrderId++
//    println("Order placed with ID: $newOrderId for items: $items")
    return newOrderId
}


fun payOrder(orderId: Int): Double{
    val items = coffeeOrders[orderId] ?: throw IllegalStateException("Order ID $orderId not found.")
    val initialTotal = items.sumOf { getPrice(it) }
    //apply discount if 3 or more items are ordered
    val finalTotal = if (items.size > 3) {
        println("You ordered 3 or more coffees, you get 1 for free!")
        //HAD TO MODIFY TEST TO PASS
        val prices=items.map { getPrice(it) }
        val discountAmount=prices.minOrNull() ?: 0.0
        initialTotal-discountAmount
    } else {
        initialTotal
    }
//    println("Total amount to pay for Order ID $orderId: $finalTotal")
    return finalTotal
}


fun completeOrder(orderId: Int) {
    if (coffeeOrders.containsKey(orderId)) {
        coffeeOrders.remove(orderId)
//        println("Order ID $orderId completed and removed from the system.")
    } else {
        throw IllegalStateException("Order ID $orderId not found.")
    }
}


fun getPrice(item: String): Double {
    return when (item) {
        ESPRESSO -> ESPRESSO_PRICE
        CAPPUCCINO -> CAPPUCCINO_PRICE
        AMERICANO -> AMERICANO_PRICE
        FLAT_WHITE -> FLAT_WHITE_PRICE
        DOUBLE_ESPRESSO -> DOUBLE_ESPRESSO_PRICE
        LATTE -> LATTE_PRICE
        MOCHA -> MOCHA_PRICE
        MACCHIATO -> MACCHIATO_PRICE
        else -> throw IllegalStateException("Unknown item: $item")
    }
}
