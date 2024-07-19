package com.ayeminoo.data.users.remote.model

/**
 * Represents a resource that can either contain successful data or an error.
 *
 * This is a sealed class, which allows us to represent restricted class hierarchies.
 * When used as a return type, `Resource` class can help to manage different states like success or failure.
 */
sealed interface NetworkResult<out T> {
    /**
     * Successful data.
     *
     * @property data Data of generic type T.
     */
    data class Success<T>(val data: T) : NetworkResult<T>

    /**
     * Failure.
     *
     * @property exception Exception
     */
    data class Error(val exception: Exception) : NetworkResult<Nothing>
}