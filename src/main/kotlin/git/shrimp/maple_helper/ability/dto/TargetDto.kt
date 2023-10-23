package git.shrimp.maple_helper.ability.dto

import git.shrimp.maple_helper.global.model.OptionLevel

class TargetDto(
    val id: Int,
    val level: OptionLevel,
    val numeric: Array<Int>,
) { }
