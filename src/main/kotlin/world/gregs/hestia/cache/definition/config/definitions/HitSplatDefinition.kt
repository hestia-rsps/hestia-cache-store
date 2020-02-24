package world.gregs.hestia.cache.definition.config.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.Reader

class HitSplatDefinition : Definition {
    var anInt3214: Int = 0
    var fade = -1
    var left: Int = -1
    var middle: Int = -1
    var icon: Int = -1
    var amount = ""
    var offsetX: Int = 0
    var right: Int = -1
    var textColour: Int = 16777215
    var offsetY: Int = 0
    var duration: Int = 70
    var comparisonType: Int = -1
    var font: Int = -1

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
        when (opcode) {
            1 -> font = buffer.readShort()
            2 -> textColour = buffer.readMedium()
            3 -> icon = buffer.readShort()
            4 -> left = buffer.readShort()
            5 -> middle = buffer.readShort()
            6 -> right = buffer.readShort()
            7 -> offsetX = buffer.readUnsignedShort()
            8 -> amount = buffer.readString()
            9 -> duration = buffer.readShort()
            10 -> offsetY = buffer.readUnsignedShort()
            11 -> fade = 0
            12 -> comparisonType = buffer.readUnsignedByte()
            13 -> anInt3214 = buffer.readUnsignedShort()
            14 -> fade = buffer.readShort()
        }
    }
}