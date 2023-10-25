package git.shrimp.maple_helper_core.ability.dto

import git.shrimp.maple_helper_core.global.model.OptionLevel

class TargetDto(
    val id: Int,
    val level: OptionLevel,
    val numeric: List<Int>,
) {}
