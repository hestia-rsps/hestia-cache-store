package world.gregs.hestia.cache.definition.config.readers

import world.gregs.hestia.cache.CacheStore
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.PlayerVariableParameterDefinition
import java.util.concurrent.ConcurrentHashMap

class PlayerVariableParameterDefinitionReader(cacheStore: CacheStore) : ConfigReader<PlayerVariableParameterDefinition>(cacheStore) {

    override val archive: Int = 16

    override val cache = ConcurrentHashMap<Int, PlayerVariableParameterDefinition>()

    override fun create(id: Int, member: Boolean) = PlayerVariableParameterDefinition().apply {
        readData(archive, id)
    }
}