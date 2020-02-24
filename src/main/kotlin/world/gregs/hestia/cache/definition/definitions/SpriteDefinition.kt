package world.gregs.hestia.cache.definition.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.BufferReader
import world.gregs.hestia.io.Reader

class SpriteDefinition : Definition {

    lateinit var sprites: Array<IndexedSprite?>

    fun decode(data: ByteArray): Array<IndexedSprite?>? {
        val buffer = BufferReader(data)
        buffer.buffer.position(data.size - 2)
        val i: Int = buffer.readShort()
        sprites = arrayOfNulls(i)
        for (i_1_ in 0 until i) sprites[i_1_] = IndexedSprite()
        buffer.buffer.position(data.size - 7 - i * 8)
        val i_2_: Int = buffer.readShort()
        val i_3_: Int = buffer.readShort()
        val i_4_: Int = (buffer.readUnsignedByte() and 0xff) + 1
        for (i_5_ in 0 until i) sprites[i_5_]!!.offsetX = buffer.readShort()
        for (i_6_ in 0 until i) sprites[i_6_]!!.offsetY = buffer.readShort()
        for (i_7_ in 0 until i) sprites[i_7_]!!.width = buffer.readShort()
        for (i_8_ in 0 until i) sprites[i_8_]!!.height = buffer.readShort()
        for (i_9_ in 0 until i) {
            val class383 = sprites[i_9_]
            class383!!.deltaWidth = i_2_ - class383.width - class383.offsetX
            class383.deltaHeight = i_3_ - class383.height - class383.offsetY
        }
        buffer.buffer.position(data.size - 7 - i * 8 - (i_4_ - 1) * 3)
        val `is` = IntArray(i_4_)
        for (i_10_ in 1 until i_4_) {
            `is`[i_10_] = buffer.readMedium()
            if (`is`[i_10_] == 0) {
                `is`[i_10_] = 1
            }
        }
        for (i_11_ in 0 until i) sprites[i_11_]!!.palette = `is`
        buffer.buffer.position(0)
        for (i_12_ in 0 until i) {
            val class383 = sprites[i_12_]
            val i_13_ = class383!!.width * class383.height
            class383.raster = ByteArray(i_13_)
            val i_14_: Int = buffer.readUnsignedByte()
            if (i_14_ and 0x2 == 0) {
                if (i_14_ and 0x1 == 0) {
                    for (i_15_ in 0 until i_13_) class383.raster[i_15_] = buffer.readByte().toByte()
                } else {
                    for (i_16_ in 0 until class383.width) {
                        for (i_17_ in 0 until class383.height) class383.raster[i_16_ + i_17_ * class383.width] =
                            buffer.readByte().toByte()
                    }
                }
            } else {
                var bool = false
                class383.alpha = ByteArray(i_13_)
                if (i_14_ and 0x1 == 0) {
                    for (i_18_ in 0 until i_13_) class383.raster[i_18_] = buffer.readByte().toByte()
                    for (i_19_ in 0 until i_13_) {
                        class383.alpha!![i_19_] = buffer.readByte().toByte()
                        val b = class383.alpha!![i_19_]
                        bool = bool or (b.toInt() != -1)
                    }
                } else {
                    for (i_20_ in 0 until class383.width) {
                        for (i_21_ in 0 until class383.height) class383.raster[i_20_ + i_21_ * class383.width] =
                            buffer.readByte().toByte()
                    }
                    for (i_22_ in 0 until class383.width) {
                        for (i_23_ in 0 until class383.height) {
                            class383.alpha!![i_22_ + i_23_ * class383.width] = buffer.readByte().toByte()
                            val b = class383.alpha!![i_22_ + i_23_ * class383.width]
                            bool = bool or (b.toInt() != -1)
                        }
                    }
                }
                if (!bool) {
                    class383.alpha = null
                }
            }
        }
        return sprites
    }

    override fun readValueLoop(buffer: Reader, member: Boolean) {
    }

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
    }
}