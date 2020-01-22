package world.gregs.hestia.cache.definition.config.readers

import world.gregs.hestia.cache.CacheStore
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.ItemContainerDefinition
import java.util.concurrent.ConcurrentHashMap

class ItemContainerDefinitionReader(cacheStore: CacheStore) : ConfigReader<ItemContainerDefinition>(cacheStore) {

    override val archive: Int = 5

    override val cache = ConcurrentHashMap<Int, ItemContainerDefinition>()

    override fun create(id: Int, member: Boolean) = ItemContainerDefinition().apply {
        readData(archive, id)
    }
}