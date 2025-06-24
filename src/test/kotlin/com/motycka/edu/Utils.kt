package com.motycka.edu

import java.io.ByteArrayOutputStream
import java.io.PrintStream

fun captureStdout(block: () -> Unit): String {
    val originalOut = System.out
    val outputStream = ByteArrayOutputStream()
    System.setOut(PrintStream(outputStream))

    try {
        block()
    } finally {
        System.setOut(originalOut)
    }

    return outputStream
        .toString()
        .replace("\r\n", "\n") // normalize line endings (important for CI)
        .trimEnd()             // avoid test failure due to trailing newlines
        //thanks to chatgpt :)
}
