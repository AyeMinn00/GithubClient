package com.ayeminoo.githubclient.navigation.destination

sealed class Destination(
    val path: String,
    private val arguments: List<String> = emptyList(),
    private val optionalArguments: List<String> = emptyList()
) {

    val route: String
        get() = buildRoute(path, arguments, optionalArguments)

    /**
     * Replace key with value after building route for arguments
     *
     * @param args Map to replace value
     */
    fun address(
        args: Map<String, Any>,
        valueTransformer: (String, Any) -> String = { _, value -> value.toString() }
    ): String {
        var address: String = route

        args.forEach { (key, value) ->
            address = address.replace("{$key}", valueTransformer(key, value))
        }

        return address
    }

    override fun toString(): String {
        return route
    }

    companion object {
        fun buildRoute(
            path: String,
            arguments: List<String>,
            optionalArguments: List<String>
        ) = StringBuilder(path).apply {
            arguments.forEach { arg ->
                append("/{$arg}")
            }
            if (optionalArguments.isNotEmpty()) {
                append("?")
            }
            optionalArguments
                .filter { str -> str.isNotBlank() }
                .forEachIndexed { index, optArg ->
                    if (index != 0) {
                        append("&")
                    }
                    append("$optArg=$optArg")
                }
        }.toString()
    }
}
