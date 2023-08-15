import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter
import kotlin.system.exitProcess

fun main() {
    val fileChooser = JFileChooser()
    val filter = FileNameExtensionFilter("Text Files", "txt")
    fileChooser.fileFilter = filter

    val result = fileChooser.showOpenDialog(null)

    if (result == JFileChooser.APPROVE_OPTION) {
        val selectedFile = fileChooser.selectedFile
        val selectedFilePath = selectedFile.absolutePath
        println("Selected file path: $selectedFilePath")

        val content = selectedFile.readText()
        val outputDirectory = selectedFile.parentFile

        val chunks = splitTextIntoChunks(content, 3000)

        chunks.forEachIndexed { index, chunk ->
            val outputFileName = "temp$index.txt"
            val outputFile = File(outputDirectory, outputFileName)
            outputFile.writeText(chunk)
        }

        println("Text split and saved into ${chunks.size} files.")
        exitProcess(0)
    }
}

fun splitTextIntoChunks(text: String, chunkSize: Int): List<String> {
    val chunks = mutableListOf<String>()
    var currentIndex = 0

    while (currentIndex < text.length) {
        val endIndex = if (currentIndex + chunkSize > text.length) {
            text.length
        } else {
            currentIndex + chunkSize
        }
        val chunk = text.substring(currentIndex, endIndex)
        chunks.add(chunk)
        currentIndex = endIndex
    }

    return chunks
}
