package com.motycka.edu.lesson02

/*
 Coffee Shop Menu
 */

const val ESPRESSO = "Espresso"
const val DOUBLE_ESPRESSO = "Double Espresso"
const val CAPPUCCINO = "Cappuccino"
const val LATTE = "Latte"
const val MACCHIATO = "Macchiato"
const val MOCHA = "Mocha"
const val FLAT_WHITE = "Flat White"
const val AMERICANO = "Americano"




/*
    1. Create a list to store coffee menu items
    2. Initialize the list with constants defined above
    3. Define a mutable map to store orders, use: mutableMapOf<Int, List<String>>()
    4. Print the coffee menu items
    5. Create order1 with, 1x ESPRESSO, 2x CAPPUCCINO, and 1x AMERICANO
    6. Add order 1 to orders map
    7. Create order2 with, 1x ESPRESSO, 1x DOUBLE_ESPRESSO, and 1x FLAT_WHITE
    8. Add order 2 to orders map
    9. Print the orders placed, uncomment the println statement at the end
 */
val coffeeMenu: List<String> = listOf(
    ESPRESSO,
    DOUBLE_ESPRESSO,
    CAPPUCCINO,
    LATTE,
    MACCHIATO,
    MOCHA,
    FLAT_WHITE,
    AMERICANO)

fun collections() {
    val orders = mutableMapOf<Int, List<String>>()
    println("\nWelcome to the Coffee Shop! Here is our menu:")

    coffeeMenu.forEach { item ->
        println("- $item")
    }

    val order1 = listOf(ESPRESSO, CAPPUCCINO, CAPPUCCINO, AMERICANO)
    val order2 = listOf(ESPRESSO, DOUBLE_ESPRESSO, FLAT_WHITE)
    orders[1] = order1
    orders[2] = order2


    println("\nOrders placed:")
    orders.forEach {
        println("Order ID: ${it.key}, Items: ${it.value}")
    }
}

fun main() {
    collections()
}
