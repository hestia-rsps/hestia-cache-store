package world.gregs.hestia.cache.definition.config.readers

import org.displee.CacheLibrary
import world.gregs.hestia.cache.Configs.IDENTITY_KIT
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.IdentityKitDefinition
import java.util.concurrent.ConcurrentHashMap

class IdentityKitDefinitionReader(cacheStore: CacheLibrary) : ConfigReader<IdentityKitDefinition>(cacheStore) {

    override val archive = IDENTITY_KIT

    override val cache = ConcurrentHashMap<Int, IdentityKitDefinition>()

    override fun create(id: Int, member: Boolean) = IdentityKitDefinition().apply {
        readData(archive, id)
    }
}