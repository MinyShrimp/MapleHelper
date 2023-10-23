package git.shrimp.maple_helper.core.ability.dto

import git.shrimp.maple_helper.core.global.model.OptionLevel

class TargetDto(
    val id: Int,
    val level: OptionLevel,
    val numeric: Array<Int>,
) { }
