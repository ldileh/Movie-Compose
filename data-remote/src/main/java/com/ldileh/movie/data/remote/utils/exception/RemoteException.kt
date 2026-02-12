package com.ldileh.movie.data.remote.utils.exception

sealed class RemoteException : Throwable() {
    object Network : RemoteException()
    data class Server(val code: Int) : RemoteException()
}