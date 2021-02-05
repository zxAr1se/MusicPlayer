package com.example.musicplayerclone.other

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun  <T> succsess(data: T?) = Resource(Status.SUCCESS, data, null)

        fun <T> error(message: String, data: T?) = Resource(Status.ERROR, data, message)

        fun <T> loading(data: T?) = Resource(Status.Loading, data, null)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    Loading
}