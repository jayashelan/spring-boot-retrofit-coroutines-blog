package nl.toefel.springbootcoroutines

import nl.toefel.springbootcoroutines.client.PostDto
import nl.toefel.springbootcoroutines.client.TodoDto

data class OverviewDto(
    val posts: List<PostDto>,
    val todos: List<TodoDto>,
)