/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.extensions

import java.security.MessageDigest

/**
 * Created by dimitrios on 04/05/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */
fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digested = md.digest(toByteArray())
    return digested.joinToString("") {
        String.format("%02x", it)
    }
}

fun String.sha1(): String {
    val md = MessageDigest.getInstance("SHA-1")
    val digested = md.digest(toByteArray())
    return digested.joinToString("") {
        String.format("%02x", it)
    }
}

fun String.sha224(): String {
    val md = MessageDigest.getInstance("SHA-224")
    val digested = md.digest(toByteArray())
    return digested.joinToString("") {
        String.format("%02x", it)
    }
}

fun String.sha256(): String {
    val md = MessageDigest.getInstance("SHA-256")
    val digested = md.digest(toByteArray())
    return digested.joinToString("") {
        String.format("%02x", it)
    }
}

fun String.sha384(): String {
    val md = MessageDigest.getInstance("SHA-384")
    val digested = md.digest(toByteArray())
    return digested.joinToString("") {
        String.format("%02x", it)
    }
}

fun String.sha512(): String {
    val md = MessageDigest.getInstance("SHA-512")
    val digested = md.digest(toByteArray())
    return digested.joinToString("") {
        String.format("%02x", it)
    }
}
