package world.gregs.hestia.cache.definition.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.Reader

class EnumDefinition : Definition {
    var defaultString = "null"
    var defaultInt = 0
    var length = 0
    var valueType = 0.toChar()
    var map: HashMap<Int, Any>? = null
    var keyType = 0.toChar()

    fun getKey(value: Any): Int {
        return map?.filter { it.value == value }?.toList()?.firstOrNull()?.first ?: -1
    }

    fun getInt(id: Int): Int {
        return map?.get(id) as? Int ?: defaultInt
    }

    fun getString(id: Int): String {
        return map?.get(id) as? String ?: defaultString
    }

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
        when (opcode) {
            1 -> keyType = byteToChar(buffer.readByte().toByte())
            2 -> valueType = byteToChar(buffer.readByte().toByte())
            3 -> defaultString = buffer.readString()
            4 -> defaultInt = buffer.readInt()
            5, 6 -> {
                length = buffer.readShort()
                val hashtable = HashMap<Int, Any>(calculateCapacity(length))
                repeat(length) {
                    val id = buffer.readInt()
                    hashtable[id] = if (opcode == 5) {
                        buffer.readString()
                    } else {
                        buffer.readInt()
                    }
                }
                map = hashtable
            }
            7 -> {
                val size = buffer.readShort()
                length = buffer.readShort()
                val strings = HashMap<Int, Any>(size)
                repeat(length) {
                    val index = buffer.readShort()
                    strings[index] = buffer.readString()
                }
                map = strings
            }
            8 -> {
                val size = buffer.readShort()
                length = buffer.readShort()
                val integers = HashMap<Int, Any>(size)
                repeat(length) {
                    val index = buffer.readShort()
                    integers[index] = buffer.readInt()
                }
                map = integers
            }
        }
    }
}