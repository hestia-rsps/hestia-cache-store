package world.gregs.hestia.cache.definition.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.Reader

class ObjectDefinition : Definition {
    var anInt2958: Int = 0
    var aByte2960: Byte = 0
    var aBoolean2961 = false
    var anInt2962: Int = 0
    var anInt2963 = 0
    var anInt2964: Int = 0
    var modelIds: ByteArray? = null
    var offsetX: Int = 0
    var modelSizeX: Int = 128
    var supportItems: Int = -1
    var anInt2971: Int = 0
    var aBoolean2972 = true
    var modelSizeZ = 128
    var aByte2974: Byte = 0
    var anInt2975: Int = 0
    var culling: Int = -1
    lateinit var options: Array<String?>
    var anIntArray2981: IntArray? = null
    var anInt2983: Int = 0
    var configObjectIds: IntArray? = null
    var offsetY: Int = 0
    var sizeY: Int = 1
    var anInt2987: Int = -1
    var modifiedModelTextures: ShortArray? = null
    var anInt2989: Int = 0
    var aBoolean2990: Boolean = false
    var aBoolean2992: Boolean = false
    var aBoolean2993: Boolean = false
    var mapDefinitionId: Int = -1
    var anIntArray2995: IntArray? = null
    var recolourPalette: ByteArray? = null
    var aBoolean2998: Boolean = false
    var name: String = "null"
    var animateImmediately: Boolean = true
    var isMembers: Boolean = false
    var mapscene: Int = -1
    var invertMapScene: Boolean = false
    var anInt3008: Int = -1
    var modelSizeY: Int = 128
    var solid: Int = 2
    var offsetMultiplier: Int = 64
    var anInt3012: Int = 0
    var anInt3013: Int = -1
    var params: HashMap<Long, Any>? = null
    var anInt3015: Int = -1
    var ignoreClipOnAlternativeRoute: Boolean = false
    var varbitIndex: Int = -1
    var anInt3018: Int = 0
    var animations: IntArray? = null
    var anInt3020: Int = 256
    var anInt3023: Int = -1
    var anInt3024: Int = 255
    var modifiedColours: ShortArray? = null
    var contrast: Int = 0
    var contouredGround: Byte = 0
    var id = 0
    var modelTypes: Array<IntArray?>? = null
    var anInt3032: Int = 960
    var castsShadow: Boolean = true
    var projectileClipped: Boolean = true
    var configId: Int = -1
    var anIntArray3036: IntArray? = null
    var offsetZ: Int = 0
    var anInt3038: Int = -1
    var mirrored: Boolean = false
    var blockFlag: Int = 0
    var brightness: Int = 0
    var originalModelTextures: ShortArray? = null
    var aByte3045: Byte = 0
    var originalColours: ShortArray? = null
    var anInt3050: Int = 256
    var obstructsGround: Boolean = false
    var aByte3052: Byte = 0
    var delayShading: Boolean = false
    var sizeX: Int = 1
    var aBoolean3056: Boolean = false
    var interactive: Int = -1

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
        when (opcode) {
            1, 5 -> {
                if (opcode == 5 && lowDetail) {
                    skip(buffer)
                }
                val length = buffer.readUnsignedByte()
                modelIds = ByteArray(length)
                modelTypes = arrayOfNulls(length)
                repeat(length) { count ->
                    modelIds!![count] = buffer.readByte().toByte()
                    val size = buffer.readUnsignedByte()
                    modelTypes!![count] = IntArray(size)
                    repeat(size) { index ->
                        modelTypes!![count]!![index] = buffer.readShort()
                    }
                }
                if (opcode == 5 && !lowDetail) {
                    skip(buffer)
                }
            }
            2 -> name = buffer.readString()
            14 -> sizeX = buffer.readUnsignedByte()
            15 -> sizeY = buffer.readUnsignedByte()
            17 -> {
                projectileClipped = false
                solid = 0
            }
            18 -> projectileClipped = false
            19 -> interactive = buffer.readUnsignedByte()
            21 -> contouredGround = 1.toByte()
            22 -> delayShading = true
            23 -> culling = 1
            24 -> {
                val length = buffer.readShort()
                if (length != 65535) {
                    animations = intArrayOf(length)
                }
            }
            27 -> solid = 1
            28 -> offsetMultiplier = buffer.readUnsignedByte() shl 2
            29 -> brightness = buffer.readByte()
            in 30..34 -> options[opcode - 30] = buffer.readString()
            39 -> contrast = 5 * buffer.readByte()
            40 -> {
                val length = buffer.readUnsignedByte()
                modifiedColours = ShortArray(length)
                originalColours = ShortArray(length)
                repeat(length) { count ->
                    originalColours!![count] = buffer.readShort().toShort()
                    modifiedColours!![count] = buffer.readShort().toShort()
                }
            }
            41 -> {
                val length = buffer.readUnsignedByte()
                modifiedModelTextures = ShortArray(length)
                originalModelTextures = ShortArray(length)
                repeat(length) { count ->
                    originalModelTextures!![count] = buffer.readShort().toShort()
                    modifiedModelTextures!![count] = buffer.readShort().toShort()
                }
            }
            42 -> {
                val length = buffer.readUnsignedByte()
                recolourPalette = ByteArray(length)
                repeat(length) { count ->
                    recolourPalette!![count] = buffer.readByte().toByte()
                }
            }
            62 -> mirrored = true
            64 -> castsShadow = false
            65 -> modelSizeX = buffer.readShort()
            66 -> modelSizeZ = buffer.readShort()
            67 -> modelSizeY = buffer.readShort()
            69 -> blockFlag = buffer.readUnsignedByte()
            70 -> offsetX = buffer.readUnsignedShort() shl 2
            71 -> offsetZ = buffer.readUnsignedShort() shl 2
            72 -> offsetY = buffer.readUnsignedShort() shl 2
            73 -> obstructsGround = true
            74 -> ignoreClipOnAlternativeRoute = true
            75 -> supportItems = buffer.readUnsignedByte()
            77, 92 -> {
                varbitIndex = buffer.readShort()
                if (varbitIndex == 65535) {
                    varbitIndex = -1
                }
                configId = buffer.readShort()
                if (configId == 65535) {
                    configId = -1
                }
                var last = -1
                if (opcode == 92) {
                    last = buffer.readShort()
                    if (last == 65535) {
                        last = -1
                    }
                }
                val length = buffer.readUnsignedByte()
                configObjectIds = IntArray(length + 2)
                var count = 0
                while (length >= count) {
                    configObjectIds!![count] = buffer.readShort()
                    if (configObjectIds!![count] == 65535) {
                        configObjectIds!![count] = -1
                    }
                    count++
                }
                configObjectIds!![length + 1] = last
            }
            78 -> {
                anInt3015 = buffer.readShort()
                anInt3012 = buffer.readUnsignedByte()
            }
            79 -> {
                anInt2989 = buffer.readShort()
                anInt2971 = buffer.readShort()
                anInt3012 = buffer.readUnsignedByte()
                val length = buffer.readUnsignedByte()
                anIntArray3036 = IntArray(length)
                repeat(length) { count ->
                    anIntArray3036!![count] = buffer.readShort()
                }
            }
            81 -> {
                contouredGround = 2.toByte()
                anInt3023 = 256 * buffer.readUnsignedByte()
            }
            82 -> aBoolean2990 = true
            88 -> aBoolean2972 = false
            89 -> animateImmediately = false
            91 -> isMembers = true
            93 -> {
                contouredGround = 3.toByte()
                anInt3023 = buffer.readShort()
            }
            94 -> contouredGround = 4.toByte()
            95 -> {
                contouredGround = 5.toByte()
                anInt3023 = buffer.readUnsignedShort()
            }
            97 -> aBoolean3056 = true
            98 -> aBoolean2998 = true
            99 -> {
                anInt2987 = buffer.readUnsignedByte()
                anInt3008 = buffer.readShort()
            }
            100 -> {
                anInt3038 = buffer.readUnsignedByte()
                anInt3013 = buffer.readShort()
            }
            101 -> anInt2958 = buffer.readUnsignedByte()
            102 -> mapscene = buffer.readShort()
            103 -> culling = 0
            104 -> anInt3024 = buffer.readUnsignedByte()
            105 -> invertMapScene = true
            106 -> {
                val length = buffer.readUnsignedByte()
                var total = 0
                animations = IntArray(length)
                anIntArray2995 = IntArray(length)
                repeat(length) { count ->
                    animations!![count] = buffer.readShort()
                    if (animations!![count] == 65535) {
                        animations!![count] = -1
                    }
                    anIntArray2995!![count] = buffer.readUnsignedByte()
                    total += anIntArray2995!![count]
                }
                repeat(length) { count ->
                    anIntArray2995!![count] = 65535 * anIntArray2995!![count] / total
                }
            }
            107 -> mapDefinitionId = buffer.readShort()
            in 150..154 -> {
                options[-150 + opcode] = buffer.readString()
                if (!member) {
                    options[-150 + opcode] = null
                }
            }
            160 -> {
                val length = buffer.readUnsignedByte()
                anIntArray2981 = IntArray(length)
                repeat(length) { count ->
                    anIntArray2981!![count] = buffer.readShort()
                }
            }
            162 -> {
                contouredGround = 3.toByte()
                anInt3023 = buffer.readInt()
            }
            163 -> {
                aByte2974 = buffer.readByte().toByte()
                aByte3045 = buffer.readByte().toByte()
                aByte3052 = buffer.readByte().toByte()
                aByte2960 = buffer.readByte().toByte()
            }
            164 -> anInt2964 = buffer.readUnsignedShort()
            165 -> anInt2963 = buffer.readUnsignedShort()
            166 -> anInt3018 = buffer.readUnsignedShort()
            167 -> anInt2983 = buffer.readShort()
            168 -> aBoolean2961 = true
            169 -> aBoolean2993 = true
            170 -> anInt3032 = buffer.readSmart()
            171 -> anInt2962 = buffer.readSmart()
            173 -> {
                anInt3050 = buffer.readShort()
                anInt3020 = buffer.readShort()
            }
            177 -> aBoolean2992 = true
            178 -> anInt2975 = buffer.readUnsignedByte()
            249 -> {
                val length = buffer.readUnsignedByte()
                if (params == null) {
                    val initialCapacity: Int = calculateCapacity(length)
                    params = HashMap(initialCapacity)
                }
                repeat(length) {
                    val isString = buffer.readUnsignedByte() == 1
                    val id = buffer.readMedium()
                    params!![id.toLong()] = if (isString) {
                        buffer.readString()
                    } else {
                        buffer.readInt()
                    }
                }
            }
        }
    }

    fun changeValues() {
        if (interactive == -1) {
            interactive = 0
            if (modelIds != null && modelIds!!.size == 1 && modelIds!![0] == 10.toByte()) {
                interactive = 1
            }
            for (index in 0..4) {
                if (options[index] != null) {
                    interactive = 1
                    break
                }
            }
        }
        if (supportItems == -1) {
            supportItems = if (solid == 0) 0 else 1
        }
        if (animations != null || aBoolean2998 || configObjectIds != null) {
            aBoolean2992 = true
        }
    }

    companion object {
        const val lowDetail = true
    }
}