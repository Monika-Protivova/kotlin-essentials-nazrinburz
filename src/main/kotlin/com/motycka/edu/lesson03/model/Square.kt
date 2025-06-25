package com.motycka.edu.lesson03.model

/*
 Implement Square class that extends the Rectangle class.

    - It should have a constructor that takes one parameter for the side length.
    - It should override all methods from Rectangle class.
    - It should have a method to convert it to a 3D Cuboid with the same side length for width, height, and depth.-
 */

class Square(side: Double) : Rectangle(side, side) {

    val side: Double
        get() = width

    override fun area(): Double {
        return width * width
    }

    override fun perimeter(): Double {
        return 4 * width
    }

    fun to3D(): Cuboid {
        return Cuboid(width, width, width)
    }
}