package world.gregs.hestia.cache.definition.definitions

import java.awt.image.BufferedImage
import kotlin.math.max
import kotlin.math.min

class IndexedSprite {
    var width = 0
    var offsetY = 0
    var deltaHeight = 0
    var height = 0
    var deltaWidth = 0
    var offsetX = 0
    lateinit var raster: ByteArray
    lateinit var palette: IntArray
    var alpha: ByteArray? = null

    fun method4189() {
        val bs = raster
        if (alpha == null) {
            for (i in (height shr 1) - 1 downTo 0) {
                var i_24_ = i * width
                var i_25_ = (height - i - 1) * width
                for (i_26_ in -width..-1) {
                    val b = bs[i_24_]
                    bs[i_24_] = bs[i_25_]
                    bs[i_25_] = b
                    i_24_++
                    i_25_++
                }
            }
        } else {
            val bs_27_ = alpha
            for (i in (height shr 1) - 1 downTo 0) {
                var i_28_ = i * width
                var i_29_ = (height - i - 1) * width
                for (i_30_ in -width..-1) {
                    var b = bs[i_28_]
                    bs[i_28_] = bs[i_29_]
                    bs[i_29_] = b
                    b = bs_27_!![i_28_]
                    bs_27_[i_28_] = bs_27_[i_29_]
                    bs_27_[i_29_] = b
                    i_28_++
                    i_29_++
                }
            }
        }
        val i = offsetY
        offsetY = deltaHeight
        deltaHeight = i
    }

    fun method4198() {
        val bs = ByteArray(width * height)
        var i = 0
        if (alpha == null) {
            for (i_95_ in 0 until width) {
                for (i_96_ in height - 1 downTo 0)
                    bs[i++] = raster[i_95_ + i_96_ * width]
            }
            raster = bs
        } else {
            val bs_97_ = ByteArray(width * height)
            for (i_98_ in 0 until width) {
                for (i_99_ in height - 1 downTo 0) {
                    bs[i] = raster[i_98_ + i_99_ * width]
                    bs_97_[i++] = alpha!![i_98_ + i_99_ * width]
                }
            }
            raster = bs
            alpha = bs_97_
        }
        offsetY = offsetX
        offsetX = deltaHeight
        deltaHeight = deltaWidth
        deltaWidth = offsetY
        val i_100_: Int = height
        height = width
        width = i_100_
    }

    fun toBufferedImage() : BufferedImage {
        val bi = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        for (x in 0 until width) {
            for (y in 0 until height) {
                val i = x + y * width
                if(alpha == null) {
                    val colour = palette[raster[i].toInt() and 255]
                    if(colour != 0)
                        bi.setRGB(x, y, -16777216 or colour)
                } else {
                    bi.setRGB(x, y, palette[raster[i].toInt() and 255] or (alpha!![i].toInt() shl 24))
                }
            }
        }
        return bi
    }
}