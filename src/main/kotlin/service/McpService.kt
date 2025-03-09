package cc.xpr35.service

import cc.xpr35.model.TextContentDTO
import cc.xpr35.model.TextContentRequest
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.ai.tool.annotation.Tool
import org.springframework.ai.tool.annotation.ToolParam
import org.springframework.stereotype.Service

@Service
class McpService(private val textContentService: TextContentService) {

    @Tool(description = "Get text")
    fun getText(): TextContentDTO? = transaction {
        textContentService.getText()
    }

    @Tool(description = "Update text")
    fun updateText(
        @ToolParam(description = "Text content") content: String
    ): TextContentDTO? = transaction {
        textContentService.updateText(request = TextContentRequest(content))
    }
}