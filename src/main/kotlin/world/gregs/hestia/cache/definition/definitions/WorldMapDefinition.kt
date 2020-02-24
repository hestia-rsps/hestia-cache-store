package world.gregs.hestia.cache.definition.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.Reader
import java.util.*

class WorldMapDefinition : Definition {
    var maxX = 0
    var position: Int = -1
    var map: String = ""
    var minY = 12800
    var minX = 12800
    var anInt9542: Int = -1
    var name: String = ""
    var static: Boolean = false
    var maxY = 0
    var id: Int = -1
    var anInt9547: Int = -1
    var sections: LinkedList<WorldMapSectionDefinition>? = null

    override fun readValueLoop(buffer: Reader, member: Boolean) {
        map = buffer.readString()
        name = buffer.readString()
        position = buffer.readInt()
        anInt9542 = buffer.readInt()//Size?
        static = buffer.readUnsignedBoolean()
        anInt9547 = buffer.readUnsignedByte()//Always zero except "Braindeath Island" which is -1
        buffer.readUnsignedByte()

        if (anInt9547 == 255) {
            anInt9547 = 0
        }
        sections = LinkedList()
        val length = buffer.readUnsignedByte()
        repeat(length) {
            sections!!.addLast(WorldMapSectionDefinition(buffer.readUnsignedByte(), buffer.readShort(), buffer.readShort(), buffer.readShort(), buffer.readShort(), buffer.readShort(), buffer.readShort(), buffer.readShort(), buffer.readShort()))
        }
    }

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun changeValues() {
        maxY = 0
        minX = 12800
        minY = 12800
        maxX = 0

        sections!!.forEach { worldMapAreaDefinition ->
            if (minY > worldMapAreaDefinition.startY) {
                minY = worldMapAreaDefinition.startY
            }
            if (worldMapAreaDefinition.endX > maxX) {
                maxX = worldMapAreaDefinition.endX
            }
            if (maxY < worldMapAreaDefinition.endY) {
                maxY = worldMapAreaDefinition.endY
            }
            if (worldMapAreaDefinition.startX < minX) {
                minX = worldMapAreaDefinition.startX
            }
        }
    }
}