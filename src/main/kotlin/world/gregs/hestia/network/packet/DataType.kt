package world.gregs.hestia.network.packet

enum class DataType(val length: Int) {
    BYTE(1),
    SHORT(2),
    MEDIUM(3),
    INT(4),
    LONG(8);
}