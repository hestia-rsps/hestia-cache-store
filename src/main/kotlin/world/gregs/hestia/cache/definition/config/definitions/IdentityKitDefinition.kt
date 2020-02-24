package world.gregs.hestia.cache.definition.config.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.Reader

class IdentityKitDefinition : Definition {
    var originalColours: ShortArray? = null
    var modifiedTextureColours: ShortArray? = null
    var originalTextureColours: ShortArray? = null
    var modelIds: IntArray? = null
    var modifiedColours: ShortArray? = null
    val headModels = intArrayOf(-1, -1, -1, -1, -1)

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
        when(opcode) {
            1 -> buffer.readUnsignedByte()
            2 -> {
                val length = buffer.readUnsignedByte()
                modelIds = IntArray(length)
                repeat(length) { count ->
                    modelIds!![count] = buffer.readUnsignedShort()
                }
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
            in 60..69 -> headModels[opcode - 60] = buffer.readUnsignedShort()
        }
    }
}