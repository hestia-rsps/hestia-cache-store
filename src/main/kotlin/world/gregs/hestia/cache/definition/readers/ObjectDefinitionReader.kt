package world.gregs.hestia.cache.definition.readers

import world.gregs.hestia.cache.CacheStore
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.ObjectDefinition
import java.util.concurrent.ConcurrentHashMap

class ObjectDefinitionReader(cacheStore: CacheStore) : DefinitionReader<ObjectDefinition> {

    override val index = cacheStore.getIndex(16)

    override val cache = ConcurrentHashMap<Int, ObjectDefinition>()

    override fun create(id: Int, member: Boolean) = ObjectDefinition().apply {
        this.id = id
        this.options = defaultOptions.clone()

        readData(id ushr 8, id and 0xff, member)

        changeValues()
        if (ignoreClipOnAlternativeRoute) {
            solid = 0
            projectileClipped = false
        }
        if (!member && isMembers) {
            options = arrayOfNulls(6)
            anIntArray2981 = null
        }
    }

    companion object {
        private val defaultOptions = arrayOf(null, null, null, null, null, "Examine")
    }
}