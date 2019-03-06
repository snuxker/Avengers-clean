package com.prapagorn.example.avengers.presenter.entity

import com.prapagorn.example.avengers.presenter.entity.Status.*

data class Result <out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Result<T> {
            return Result(ERROR, data, msg)
        }

        fun <T> loading(data: T?): Result<T> {
            return Result(LOADING, data, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}