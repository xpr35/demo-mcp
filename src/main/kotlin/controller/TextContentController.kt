package cc.xpr35.controller

import cc.xpr35.model.TextContentDTO
import cc.xpr35.model.TextContentRequest
import cc.xpr35.service.TextContentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/text")
class TextContentController(private val textContentService: TextContentService) {

    @GetMapping
    fun getText(): ResponseEntity<TextContentDTO> {
        val text = textContentService.getText()
        return if (text != null) {
            ResponseEntity.ok(text)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun updateText(@RequestBody request: TextContentRequest): ResponseEntity<TextContentDTO> {
        val updatedText = textContentService.updateText(request = request)
        return ResponseEntity.ok(updatedText)
    }
}