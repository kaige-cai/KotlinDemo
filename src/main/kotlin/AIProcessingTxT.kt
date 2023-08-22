import com.hankcs.hanlp.HanLP
import java.awt.FileDialog
import java.awt.Frame
import java.io.*

fun main() {
    val filePath = chooseTxT() ?: return
    val text = readTxTFromFile(filePath)

    val segmentedText = segmentText(text)
    println("分词后的文本:")
    println(segmentedText)

    val synonymsMergedText = mergeSynonyms(segmentedText)
    println("同义句子合并后的文本:")
    println(synonymsMergedText)

    val outputFileName = "处理后的文本.txt"
    saveToFile(synonymsMergedText, filePath, outputFileName)
    println("已保存处理后的文本到文件: ${File(filePath).parent}/${outputFileName}")

}

fun chooseTxT(): String? {
    val fileDialog = FileDialog(null as Frame?, "打开文本文件", FileDialog.LOAD)
    fileDialog.isVisible = true

    return if (fileDialog.file != null) {
        fileDialog.directory + fileDialog.file
    } else {
        null
    }
}

fun readTxTFromFile(filePath: String): String {
    val file = FileReader(filePath)
    val reader = BufferedReader(file)
    val lines = mutableListOf<String>()
    var line: String?
    while (reader.readLine().also { line = it } != null) {
        lines.add(line!!)
    }
    reader.close()
    return lines.joinToString("\n")
}

fun segmentText(text: String): List<String> {
    val termList = HanLP.segment(text)
    val segmentedText = mutableListOf<String>()
    for (term in termList) {
        segmentedText.add(term.word)
    }
    return segmentedText
}

fun mergeSynonyms(segmentedText: List<String>): String {
    // 在这里实现同义句子合并的逻辑，使用 segmentedText 和 HanLP 的工具
    return segmentedText.joinToString("")
}

fun saveToFile(content: String, filePath: String, fileName: String) {
    val parentDirectory = File(filePath).parentFile
    val outputFile = File(parentDirectory, fileName)

    val writer = BufferedWriter(FileWriter(outputFile))
    writer.write(content)
    writer.close()
}
