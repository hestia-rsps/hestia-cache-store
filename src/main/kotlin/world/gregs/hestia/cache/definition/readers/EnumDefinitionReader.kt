package world.gregs.hestia.cache.definition.readers

import world.gregs.hestia.cache.CacheStore
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.EnumDefinition
import java.util.concurrent.ConcurrentHashMap

class EnumDefinitionReader(cacheStore: CacheStore) : DefinitionReader<EnumDefinition> {

    override val index = cacheStore.getIndex(17)

    override val cache = ConcurrentHashMap<Int, EnumDefinition>()

    override fun create(id: Int, member: Boolean) = EnumDefinition().apply {
        readData(id ushr 8, id and 0xff)
    }
}