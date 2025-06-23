package com.motycka.edu.lesson02

val coffeeOrders = mutableMapOf<Int, List<String>>()

fun main() {
    // You can write code here to try the functions
    processOrder(listOf(ESPRESSO, CAPPUCCINO, CAPPUCCINO, AMERICANO), 20.0)
    processOrder(listOf(ESPRESSO, FLAT_WHITE, AMERICANO), 10.0)
    // processOrder(listOf(ESPRESSO, ESPRESSO, DOUBLE_ESPRESSO), 5.0) // will fail due to insufficient payment
}

/* Implement the functions below */

fun processOrder(items: List<String>, payment: Double): Double {
    val orderId = placeOrder(items)
    val totalToPay = payOrder(orderId)

    val change = payment-totalToPay

    completeOrder(orderId)

    return change
}

// TODO Implement placerOrder(items: List<String>): Int
fun placeOrder(items: List<String>): Int {
    val orderId = coffeeOrders.size + 1 // Simple order ID generation
    coffeeOrders[orderId] = items
    println("Order placed with ID: $orderId for items: $items")
    return orderId
}

// TODO Implement payOrder(orderId: Int): Double
fun payOrder(orderId: Int): Double{
    val items = coffeeOrders[orderId] ?: throw IllegalArgumentException("Order ID $orderId not found")
    val total = items.sumOf { getPrice(it) }
    //apply discount if 3 or more items are ordered
    val discount = if (items.size >= 3) {
        println("You ordered 3 or more items, you get 1 for free!")
        val discount= getPrice(items.minOrNull() ?: throw IllegalArgumentException("No items in order"))
        total-discount
    } else {
        total
    }

    println("Total amount to pay for Order ID $orderId: $total")
    return total

}

// TODO Implement completeOrder(orderId: Int)
fun completeOrder(orderId: Int) {
    if (coffeeOrders.containsKey(orderId)) {
        coffeeOrders.remove(orderId)
        println("Order ID $orderId completed and removed from the system.")
    } else {
        throw IllegalArgumentException("Order ID $orderId not found")
    }
}


fun getPrice(item: String): Double {
    return when (item) {
        ESPRESSO -> 5.0
        CAPPUCCINO -> 6.0
        AMERICANO -> 4.0
        FLAT_WHITE -> 5.5
        DOUBLE_ESPRESSO -> 7.0
        else -> throw IllegalArgumentException("Unknown item: $item")
    }
}
