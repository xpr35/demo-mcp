package cc.xpr35.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object TextContents : LongIdTable() {
    val content = text("content")
    val createdAt = datetime("created_at").default(LocalDateTime.now())
    val updatedAt = datetime("updated_at").default(LocalDateTime.now())
}

class TextContent(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TextContent>(TextContents)

    var content by TextContents.content
    var createdAt by TextContents.createdAt
    var updatedAt by TextContents.updatedAt
}

data class TextContentRequest(
    val content: String
)