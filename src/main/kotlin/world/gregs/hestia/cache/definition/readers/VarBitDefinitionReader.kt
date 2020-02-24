package world.gregs.hestia.cache.definition.readers

import org.displee.CacheLibrary
import world.gregs.hestia.cache.Indices.VAR_BIT
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.VarBitDefinition
import java.util.concurrent.ConcurrentHashMap

class VarBitDefinitionReader(cacheStore: CacheLibrary) : DefinitionReader<VarBitDefinition> {

    override val index = cacheStore.getIndex(VAR_BIT)

    override val cache = ConcurrentHashMap<Int, VarBitDefinition>()

    override fun create(id: Int, member: Boolean) = VarBitDefinition().apply {
        readData(id ushr 10, 0x3ff and id)
    }
}