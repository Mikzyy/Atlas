plugins {
    `java-library`
    id("io.freefair.lombok")
}

dependencies {
    compileOnlyApi("org.jetbrains:annotations:26.0.1")

    compileOnlyApi("net.kyori:adventure-api:4.21.0") {
        exclude(module = "adventure-bom")
        exclude(module = "checker-qual")
        exclude(module = "annotations")
    }

    compileOnlyApi("net.kyori:adventure-text-serializer-legacy:4.21.0") {
        exclude(module = "adventure-bom")
        exclude(module = "adventure-api")
    }

    compileOnlyApi("net.kyori:adventure-text-serializer-plain:4.21.0") {
        exclude(module = "adventure-bom")
        exclude(module = "adventure-api")
    }

    compileOnlyApi("net.kyori:adventure-text-minimessage:4.21.0") {
        exclude(module = "adventure-bom")
        exclude(module = "adventure-api")
    }
}
