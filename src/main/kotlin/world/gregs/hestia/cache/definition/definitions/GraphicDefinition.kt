package world.gregs.hestia.cache.definition.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.Reader

class GraphicDefinition : Definition {
    var id = 0
    var modelId = 0
    var aByte2381: Byte = 0
    var ambience = 0
    var modifiedColours: ShortArray? = null
    var anInt2385: Int = -1
    var originalColours: ShortArray? = null
    var rotation: Int = 0
    var originalTextureColours: ShortArray? = null
    var contrast: Int = 0
    var animationId: Int = -1
    var modifiedTextureColours: ShortArray? = null
    var sizeZ: Int = 128
    var sizeXY: Int = 128
    var aBoolean2402: Boolean = false

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
        when (opcode) {
            1 -> modelId = buffer.readShort()
            2 -> animationId = buffer.readShort()
            4 -> sizeXY = buffer.readShort()
            5 -> sizeZ = buffer.readShort()
            6 -> rotation = buffer.readShort()
            7 -> ambience = buffer.readUnsignedByte()
            8 -> contrast = buffer.readUnsignedByte()
            9 -> {
                aByte2381 = 3.toByte()
                anInt2385 = 8224
            }
            10 -> aBoolean2402 = true
            11 -> aByte2381 = 1.toByte()
            12 -> aByte2381 = 4.toByte()
            13 -> aByte2381 = 5.toByte()
            14 -> {
                aByte2381 = 2.toByte()
                anInt2385 = 256 * buffer.readUnsignedByte()
            }
            15 -> {
                aByte2381 = 3.toByte()
                anInt2385 = buffer.readShort()
            }
            16 -> {
                aByte2381 = 3.toByte()
                anInt2385 = buffer.readInt()
            }
            40 -> {
                val length = buffer.readUnsignedByte()
                modifiedColours = ShortArray(length)
                originalColours = ShortArray(length)
                repeat(length) { count ->
                    originalColours!![count] = buffer.readShort().toShort()
                    modifiedColours!![count] = buffer.readShort().toShort()
                }
            }
            41 -> {
                val length = buffer.readUnsignedByte()
                modifiedTextureColours = ShortArray(length)
                originalTextureColours = ShortArray(length)
                repeat(length) { count ->
                    originalTextureColours!![count] = buffer.readShort().toShort()
                    modifiedTextureColours!![count] = buffer.readShort().toShort()
                }
            }
        }
    }
}