package world.gregs.hestia.cache.definition.config.readers

import com.displee.cache.CacheLibrary
import world.gregs.hestia.cache.Configs.VARP
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.PlayerVariableParameterDefinition
import java.util.concurrent.ConcurrentHashMap

class PlayerVariableParameterDefinitionReader(cacheStore: CacheLibrary) : ConfigReader<PlayerVariableParameterDefinition>(cacheStore) {

    override val archive: Int = VARP

    override val cache = ConcurrentHashMap<Int, PlayerVariableParameterDefinition>()

    override fun create(id: Int, member: Boolean) = PlayerVariableParameterDefinition().apply {
        readData(archive, id)
    }
}