package world.gregs.hestia.cache.definition.config.readers

import world.gregs.hestia.cache.CacheStore
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.ClientVariableParameterDefinition
import java.util.concurrent.ConcurrentHashMap

class ClientVariableParameterDefinitionReader(cacheStore: CacheStore) : ConfigReader<ClientVariableParameterDefinition>(cacheStore) {

    override val archive: Int = 19

    override val cache = ConcurrentHashMap<Int, ClientVariableParameterDefinition>()

    override fun create(id: Int, member: Boolean) = ClientVariableParameterDefinition().apply {
        readData(archive, id)
    }
}