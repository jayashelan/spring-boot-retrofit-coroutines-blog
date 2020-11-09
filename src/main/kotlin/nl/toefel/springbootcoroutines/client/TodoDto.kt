package nl.toefel.springbootcoroutines.client

data class TodoDto(
    val userId: Long,
    val id: Long,
    val title: String,
    val completed: Boolean,
)