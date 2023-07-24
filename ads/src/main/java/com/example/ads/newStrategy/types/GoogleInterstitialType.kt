package com.example.ads.newStrategy.types

import com.example.ads.newStrategy.types.interfaces.AdID

enum class GoogleInterstitialType(
    override val id: Int,
) : AdID {
    HIGH(1),
    MEDIUM(2),
    DEFAULT(3),
}