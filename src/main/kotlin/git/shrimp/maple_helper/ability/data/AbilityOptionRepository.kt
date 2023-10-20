package git.shrimp.maple_helper.ability.data

import git.shrimp.maple_helper.ability.model.AbilityOption
import org.springframework.stereotype.Service

@Service
class AbilityOptionRepository {
    private val table = mutableMapOf<Int, AbilityOption>()

    init {
        this.add(0, "STR {0} 증가")
        this.add(1, "DEX {0} 증가")
        this.add(2, "INT {0} 증가")
        this.add(3, "LUK {0} 증가")
        this.add(4, "최대 HP {0} 증가")
        this.add(5, "최대 MP {0} 증가")
        this.add(6, "공격력 {0} 증가")
        this.add(7, "마력 {0} 증가")
        this.add(8, "크리티컬 확률 {0}% 증가")
        this.add(9, "공격 속도 단계 {0} 증가")
        this.add(10, "모든 능력치 {0} 증가")
        this.add(11, "AP를 직접 투자한 STR의 {0}% 만큼 DEX 증가")
        this.add(12, "AP를 직접 투자한 DEX의 {0}% 만큼 STR 증가")
        this.add(13, "AP를 직접 투자한 INT의 {0}% 만큼 LUK 증가")
        this.add(14, "AP를 직접 투자한 LUK의 {0}% 만큼 INT 증가")
        this.add(15, "{0} 레벨마다 공격력 1 증가")
        this.add(16, "{0} 레벨마다 마력 1 증가")
        this.add(17, "최대 HP {0}% 증가")
        this.add(18, "최대 MP {0}% 증가")
        this.add(19, "보스 몬스터 공격 시 데미지 % 증가")
        this.add(20, "일반 몬스터 공격 시 데미지 {0}% 증가")
        this.add(21, "상태 이상에 걸린 대상 공격 시 데미지 {0}% 증가")
        this.add(22, "방어력의 {0}% 만큼 데미지 고정값 증가")
        this.add(23, "스킬 사용 시 % 확률로 재사용 대기시간이 미적용")
        this.add(24, "패시브 스킬 레벨 {0} 증가")
        this.add(25, "다수 공격 스킬의 공격 대상 {0} 증가")
        this.add(26, "버프 스킬의 지속 시간 {0}% 증가")
        this.add(27, "아이템 드롭율 {0}% 증가")
        this.add(28, "메소 획득량 {0}% 증가")
        this.add(29, "STR, DEX {0} 증가")
        this.add(30, "STR, INT {0} 증가")
        this.add(31, "STR, LUK {0} 증가")
        this.add(32, "DEX, STR {0} 증가")
        this.add(33, "DEX, INT {0} 증가")
        this.add(34, "DEX, LUK {0} 증가")
        this.add(35, "INT, STR {0} 증가")
        this.add(36, "INT, DEX {0} 증가")
        this.add(37, "INT, LUK {0} 증가")
        this.add(38, "LUK, STR {0} 증가")
        this.add(39, "LUK, DEX {0} 증가")
        this.add(40, "LUK, INT {0} 증가")
    }

    fun add(
        id: Int,
        name: String
    ): AbilityOption {
        val option = AbilityOption(id, name)
        this.table[id] = option

        return option
    }

    fun get(
        id: Int
    ): AbilityOption {
        return this.table[id] ?: throw Exception("No such option: $id")
    }
}
