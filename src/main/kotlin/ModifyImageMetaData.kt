import org.apache.commons.io.FileUtils
import java.io.File
import java.text.SimpleDateFormat

fun main() {
    val imagePath = chooseFile("选择图片文件") ?: run {
        println("未选择文件，程序退出。")
        return
    }

    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val newTimestampString = "2014-10-03 13:22:18"  // 替换为你想要设置的新时间戳
    val newTimestamp = dateFormat.parse(newTimestampString).toInstant()

    try {
        val imageFile = File(imagePath)

        if (imageFile.exists()) {
            // 设置创建时间和修改时间
            FileUtils.touch(imageFile)
            imageFile.setLastModified(newTimestamp.toEpochMilli())

            println("图片时间戳修改成功！")
        } else {
            println("图片文件不存在：$imagePath")
        }
    } catch (e: Exception) {
        println("发生错误：${e.message}")
    }
}