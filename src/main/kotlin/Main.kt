package cc.xpr35
import cc.xpr35.model.TextContents
import cc.xpr35.service.McpService
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.ai.tool.ToolCallbackProvider
import org.springframework.ai.tool.method.MethodToolCallbackProvider
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling


@SpringBootApplication
@EnableAsync
@EnableScheduling
class TextProviderApplication(
) {

    @Bean
    fun initializer() = ApplicationRunner {
        transaction {
            SchemaUtils.create(TextContents)
        }
    }

    @Bean
    fun tools(textTools: McpService?): ToolCallbackProvider {
        return MethodToolCallbackProvider.builder()
            .toolObjects(textTools)
            .build()
    }
}

fun main(args: Array<String>) {
    runApplication<TextProviderApplication>(*args)
}