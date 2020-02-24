package world.gregs.hestia.cache.definition.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.Reader

/**
 * Equipment Slots Definition
 */
class BodyDefinition : Definition {
    var anIntArray4501: IntArray? = null
    var anInt4504 = -1
    var anInt4506 = -1
    var anIntArray4507: IntArray? = null
    var disabledSlots = IntArray(0)

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
        when (opcode) {
            1 -> {
                val length = buffer.readUnsignedByte()
                disabledSlots = IntArray(length)
                repeat(disabledSlots.size) { count ->
                    disabledSlots[count] = buffer.readUnsignedByte()
                }
            }
            3 -> anInt4506 = buffer.readUnsignedByte()
            4 -> anInt4504 = buffer.readUnsignedByte()
            5 -> {
                anIntArray4501 = IntArray(buffer.readUnsignedByte())
                repeat(anIntArray4501!!.size) { count ->
                    anIntArray4501!![count] = buffer.readUnsignedByte()
                }
            }
            6 -> {
                anIntArray4507 = IntArray(buffer.readUnsignedByte())
                repeat(anIntArray4507!!.size) { count ->
                    anIntArray4507!![count] = buffer.readUnsignedByte()
                }
            }
        }
    }
}