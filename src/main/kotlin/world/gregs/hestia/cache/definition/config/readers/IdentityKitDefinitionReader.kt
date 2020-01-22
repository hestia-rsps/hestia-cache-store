package world.gregs.hestia.cache.definition.config.readers

import world.gregs.hestia.cache.CacheStore
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.IdentityKitDefinition
import java.util.concurrent.ConcurrentHashMap

class IdentityKitDefinitionReader(cacheStore: CacheStore) : ConfigReader<IdentityKitDefinition>(cacheStore) {

    override val archive = 3

    override val cache = ConcurrentHashMap<Int, IdentityKitDefinition>()

    override fun create(id: Int, member: Boolean) = IdentityKitDefinition().apply {
        readData(archive, id)
    }
}