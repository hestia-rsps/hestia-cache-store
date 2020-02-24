package world.gregs.hestia.cache.definition.config.readers

import com.displee.cache.CacheLibrary
import world.gregs.hestia.cache.Configs.VARC
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.ClientVariableParameterDefinition
import java.util.concurrent.ConcurrentHashMap

class ClientVariableParameterDefinitionReader(cacheStore: CacheLibrary) : ConfigReader<ClientVariableParameterDefinition>(cacheStore) {

    override val archive: Int = VARC

    override val cache = ConcurrentHashMap<Int, ClientVariableParameterDefinition>()

    override fun create(id: Int, member: Boolean) = ClientVariableParameterDefinition().apply {
        readData(archive, id)
    }
}