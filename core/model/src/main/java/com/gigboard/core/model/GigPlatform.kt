package com.gigboard.core.model

enum class GigPlatform(
    val displayName: String,
    val icon: String,
) {
    UBER("Uber", "U"),
    DOORDASH("DoorDash", "D"),
    LYFT("Lyft", "L"),
    AMAZON_FLEX("Amazon Flex", "A"),
    SPARK_DRIVER("Spark Driver", "S"),
    INSTACART("Instacart", "I"),
    GRUBHUB("Grubhub", "G"),
}
