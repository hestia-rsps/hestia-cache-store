package world.gregs.hestia.cache.definition.readers

import org.displee.CacheLibrary
import world.gregs.hestia.cache.Indices.ENUMS
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.EnumDefinition
import java.util.concurrent.ConcurrentHashMap

class EnumDefinitionReader(cacheStore: CacheLibrary) : DefinitionReader<EnumDefinition> {

    override val index = cacheStore.getIndex(ENUMS)

    override val cache = ConcurrentHashMap<Int, EnumDefinition>()

    override fun create(id: Int, member: Boolean) = EnumDefinition().apply {
        readData(id ushr 8, id and 0xff)
    }
}