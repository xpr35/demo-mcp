package cc.xpr35.service

import cc.xpr35.model.TextContentDTO
import cc.xpr35.model.TextContentRequest
import cc.xpr35.model.TextContents
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class TextContentService {
    fun getText(): TextContentDTO? = transaction {
        TextContents.select { TextContents.id eq 1L }
            .map { TextContentDTO(it[TextContents.content]) }
            .singleOrNull()
    }

    fun updateText(request: TextContentRequest): TextContentDTO? = transaction {
        val updatedRows = TextContents.update({ TextContents.id eq 1L }) {
            it[content] = request.content
        }
        if (updatedRows == 0) {
            TextContents.insert {
                it[id] = 1L
                it[content] = request.content
            }
        }
        TextContentDTO(request.content)
    }
}