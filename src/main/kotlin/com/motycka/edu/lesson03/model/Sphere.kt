package com.motycka.edu.lesson03.model

import kotlin.math.pow

/*
 Implement Sphere class that implements Shape3D interface.
    - It should have a constructor that takes one parameter for the radius.
    - It should override all methods from Shape3D interface.
 */

class Sphere(private val radius: Double) : Shape3D {

    override fun volume(): Double {
        return (4.0 / 3.0) * Math.PI * radius.pow(3)
    }

    override fun surfaceArea(): Double {
        return 4 * Math.PI * radius.pow(2)
    }
}