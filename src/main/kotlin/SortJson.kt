@file:Suppress("PLUGIN_IS_NOT_ENABLED")

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@Serializable
data class ResultItem(
    val value: String, val title: String, val subtitle: String, val desc: String, val pic: String
)

@Serializable
data class ResultWrapper(val result: List<ResultItem>)

fun main() {
    val inputFilePath = chooseFile("选择一个JSON文件")

    if (inputFilePath != null) {
        val jsonString = String(Files.readAllBytes(Paths.get(inputFilePath)))

        val resultWrapper = Json.decodeFromString<ResultWrapper>(jsonString)

        val sortedResult = resultWrapper.result.sortedBy { it.value }

        val sortedResultWrapper = ResultWrapper(sortedResult)

        val sortedJsonString = Json.encodeToString(sortedResultWrapper)

        // 输出排序后的JSON字符串
        println(sortedJsonString)

        // 将排序后的JSON保存到一个名为 "sort.json" 的新文件中
        val outputFilePath = inputFilePath.replaceAfterLast(File.separator, "sort.json")
        saveToFile(outputFilePath, sortedJsonString)

        println("排序后的JSON已保存至: $outputFilePath")
    } else {
        println("未选择任何文件.")
    }
}
