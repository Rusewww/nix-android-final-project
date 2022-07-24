package com.nix.summer.finaly.core.entity

enum class Coffee(val water: Int, val milk: Int, val coffeeBeans: Int, val money: Float) {
    ESPRESSO(250, 0, 15, 4f),
    LATTE(350, 75, 20, 5f),
    CAPPUCCINO(200, 100, 12, 6f);
}