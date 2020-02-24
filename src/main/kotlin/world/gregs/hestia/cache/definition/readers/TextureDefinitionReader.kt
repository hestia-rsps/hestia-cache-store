package world.gregs.hestia.cache.definition.readers

import com.displee.cache.CacheLibrary
import world.gregs.hestia.cache.Indices.TEXTURE_DEFINITIONS
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.TextureDefinition
import world.gregs.hestia.io.BufferReader
import java.util.concurrent.ConcurrentHashMap

class TextureDefinitionReader(cacheStore: CacheLibrary) : DefinitionReader<TextureDefinition> {

    override val index = cacheStore.index(TEXTURE_DEFINITIONS)

    override val cache = ConcurrentHashMap<Int, TextureDefinition>()

    init {
        val data = index.archive(0)?.file(0)?.data
        if(data != null) {
            decode(data)
        }
    }

    override fun create(id: Int, member: Boolean): TextureDefinition {
        return metrics[id]!!
    }

    var metricsCount = 0
    lateinit var metrics: Array<TextureDefinition?>

    override val size: Int
        get() = metricsCount

    fun decode(data: ByteArray) {
        val buffer = BufferReader(data)
        metricsCount = buffer.readShort()
        metrics = arrayOfNulls(metricsCount)
        for(i in 0 until metricsCount) {
            if(buffer.readUnsignedBoolean()) {
                metrics[i] = TextureDefinition()
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.useTextureColour = buffer.readUnsignedByte() == 0
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.aBoolean1204 = buffer.readUnsignedBoolean()
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.aBoolean1205 = buffer.readUnsignedBoolean()
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.aByte1217 = buffer.readByte().toByte()
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.aByte1225 = buffer.readByte().toByte()
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.type = buffer.readByte().toByte()
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.aByte1213 = buffer.readByte().toByte()
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.colour = buffer.readShort()
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.aByte1211 = buffer.readByte().toByte()
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.aByte1203 = buffer.readByte().toByte()
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.aBoolean1222 = buffer.readUnsignedBoolean()
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.aBoolean1216 = buffer.readUnsignedBoolean()
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.aByte1207 = buffer.readByte().toByte()
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.aBoolean1212 = buffer.readUnsignedBoolean()
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.aBoolean1210 = buffer.readUnsignedBoolean()
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.aBoolean1215 = buffer.readUnsignedBoolean()
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.anInt1202 = buffer.readUnsignedByte()
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.anInt1206 = buffer.readInt()
            }
        }
        for(i in 0 until metricsCount) {
            if (metrics[i] != null) {
                metrics[i]!!.anInt1226 = buffer.readUnsignedByte()
            }
        }
    }
}