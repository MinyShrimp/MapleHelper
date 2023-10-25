package git.shrimp.maple_helper_core.global.converter

import jakarta.persistence.AttributeConverter

class IntListToStringConverter() : AttributeConverter<List<Int>, String> {
    override fun convertToDatabaseColumn(
        attribute: List<Int>?
    ): String {
        return attribute?.joinToString("|") { it.toString() } ?: ""
    }

    override fun convertToEntityAttribute(
        dbData: String?
    ): List<Int> {
        return dbData?.split("|")?.map { it.toInt() } ?: listOf()
    }
}
