package murat.tfkb.domain.model

sealed class ResultState<out T> {
    class LOADING<T> : ResultState<T>()
    data class ERROR(val exception: Exception) : ResultState<Nothing>()
    data class SUCCESS<out T>(val data: T) : ResultState<T>()
}
