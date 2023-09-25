import java.awt.FileDialog
import java.awt.Frame
import java.io.File

// 选择文件路径
fun chooseDirectory(title: String): String? {
    val fileDialog = FileDialog(null as Frame?, title, FileDialog.LOAD)
    fileDialog.isMultipleMode = false
    fileDialog.file = null // 清除文件名，只选择文件夹路径
    fileDialog.isVisible = true

    return if (fileDialog.directory != null) {
        fileDialog.directory
    } else {
        null
    }
}

// 选择指定文件
fun chooseFile(title: String): String? {
    val fileDialog = FileDialog(null as Frame?, title, FileDialog.LOAD)
    fileDialog.isVisible = true

    return if (fileDialog.file != null) {
        File(fileDialog.directory, fileDialog.file).absolutePath
    } else {
        null
    }
}
