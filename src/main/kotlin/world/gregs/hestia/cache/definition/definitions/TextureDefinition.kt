package world.gregs.hestia.cache.definition.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.Reader

class TextureDefinition : Definition {
    var anInt1202 = 0
    var aByte1203: Byte = 0
    var aBoolean1204 = false
    var aBoolean1205 = false
    var anInt1206 = 0
    var aByte1207: Byte = 0
    var aBoolean1210 = false
    var aByte1211: Byte = 0
    var aBoolean1212 = false
    var aByte1213: Byte = 0
    var type: Byte = 0
    var aBoolean1215 = false
    var aBoolean1216 = false
    var aByte1217: Byte = 0
    var colour: Int = 0
    var aBoolean1222 = false
    var useTextureColour = false
    var aByte1225: Byte = 0
    var anInt1226 = 0

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
    }
}