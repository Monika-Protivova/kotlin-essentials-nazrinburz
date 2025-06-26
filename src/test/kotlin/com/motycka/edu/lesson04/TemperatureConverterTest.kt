package com.motycka.edu.lesson04

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

// By convention, the name of the test class should be the name of the class under test + "Test"
class TemperatureConverterTest : StringSpec({

    "toCelsius should convert Fahrenheit to Celsius correctly" {
        TemperatureConverter.toCelsius(32.0) shouldBe 0.0
        TemperatureConverter.toCelsius(212.0) shouldBe 100.0
        TemperatureConverter.toCelsius(98.6) shouldBe 37.0
    }

    "toFahrenheit should convert Celsius to Fahrenheit correctly" {
        TemperatureConverter.toFahrenheit(0.0) shouldBe 32.0
        TemperatureConverter.toFahrenheit(100.0) shouldBe 212.0
        TemperatureConverter.toFahrenheit(37.0) shouldBe 98.6
        TemperatureConverter.toFahrenheit(-10.0) shouldBe 14.0
    }

})
