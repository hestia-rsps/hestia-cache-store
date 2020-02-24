package world.gregs.hestia.cache.definition.readers

import com.displee.cache.CacheLibrary
import world.gregs.hestia.cache.Indices.NPCS
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.NpcDefinition
import java.util.concurrent.ConcurrentHashMap

class NpcDefinitionReader(cacheStore: CacheLibrary) : DefinitionReader<NpcDefinition> {

    override val index = cacheStore.index(NPCS)

    override val cache = ConcurrentHashMap<Int, NpcDefinition>()

    override fun create(id: Int, member: Boolean) = NpcDefinition().apply {
        this.id = id
        this.options = defaultOptions.clone()

        readData(id ushr 7, 0x7f and id, member)

        changeValues()
    }

    companion object {
        private val defaultOptions = arrayOf(null, null, null, null, null, "Examine")
    }
}