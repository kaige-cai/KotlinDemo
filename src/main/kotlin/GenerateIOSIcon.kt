import java.awt.FileDialog
import java.awt.Frame
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main() {
    val sourceIconPath = chooseSourceIcon() ?: return
    val targetIconPath = chooseTargetIcon() ?: return

    val sizes = readImageSizesFromFile(targetIconPath)

    for (size in sizes) {
        val sizeValues = size.split("x").map { it.toInt() }
        val scaledImage = scaleImage(sourceIconPath, sizeValues[0], sizeValues[1])
        ImageIO.write(scaledImage, "png", File(targetIconPath))

        println("Generated icon for size $size")
    }

    println("Icons generated and saved.")
}

fun chooseSourceIcon(): String? {
    val fileDialog = FileDialog(null as Frame?, "Choose Source Icon", FileDialog.LOAD)
    fileDialog.isVisible = true

    return if (fileDialog.file != null) {
        File(fileDialog.directory, fileDialog.file).absolutePath
    } else {
        null
    }
}

fun chooseTargetIcon(): String? {
    val fileDialog = FileDialog(null as Frame?, "Choose Target Icon", FileDialog.LOAD)
    fileDialog.isVisible = true

    return if (fileDialog.file != null) {
        File(fileDialog.directory, fileDialog.file).absolutePath
    } else {
        null
    }
}

fun readImageSizesFromFile(imagePath: String): List<String> {
    val sizes = mutableListOf<String>()
    val image = ImageIO.read(File(imagePath))
    sizes.add("${image.width}x${image.height}")
    return sizes
}

fun scaleImage(imagePath: String, width: Int, height: Int): BufferedImage {
    val sourceImage = ImageIO.read(File(imagePath))
    val scaledImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val g2d = scaledImage.createGraphics()
    g2d.drawImage(sourceImage, 0, 0, width, height, null)
    g2d.dispose()
    return scaledImage
}
