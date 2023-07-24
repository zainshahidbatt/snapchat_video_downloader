package com.example.ads.newStrategy.types

import com.example.ads.newStrategy.types.interfaces.AdID

enum class GoogleRewardedType(
    override val id: Int,
) : AdID {
    HIGH(1),
    MEDIUM(2),
    DEFAULT(3),
}