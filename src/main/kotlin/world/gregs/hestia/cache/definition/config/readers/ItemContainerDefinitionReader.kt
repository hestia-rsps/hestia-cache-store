package world.gregs.hestia.cache.definition.config.readers

import com.displee.cache.CacheLibrary
import world.gregs.hestia.cache.Configs.CONTAINERS
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.ItemContainerDefinition
import java.util.concurrent.ConcurrentHashMap

class ItemContainerDefinitionReader(cacheStore: CacheLibrary) : ConfigReader<ItemContainerDefinition>(cacheStore) {

    override val archive: Int = CONTAINERS

    override val cache = ConcurrentHashMap<Int, ItemContainerDefinition>()

    override fun create(id: Int, member: Boolean) = ItemContainerDefinition().apply {
        readData(archive, id)
    }
}