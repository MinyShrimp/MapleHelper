package git.shrimp.maple_helper_core.ability.service

import git.shrimp.maple_helper_core.ability.model.AbilityOption
import git.shrimp.maple_helper_core.ability.repository.AbilityOptionRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AbilityOptionInitializeService(
    private val abilityOptionRepository: AbilityOptionRepository
) {
    private fun add(
        id: Int,
        name: String
    ) {
        this.abilityOptionRepository.save(AbilityOption(id, name))
    }

    @Transactional
    fun initialize() {
        this.abilityOptionRepository.deleteAll()

        this.add(1, "STR {0} 증가")
        this.add(2, "DEX {0} 증가")
        this.add(3, "INT {0} 증가")
        this.add(4, "LUK {0} 증가")
        this.add(5, "최대 HP {0} 증가")
        this.add(6, "최대 MP {0} 증가")
        this.add(7, "공격력 {0} 증가")
        this.add(8, "마력 {0} 증가")
        this.add(9, "크리티컬 확률 {0}% 증가")
        this.add(10, "모든 능력치 {0} 증가")
        this.add(11, "공격 속도 단계 {0} 증가")
        this.add(12, "AP를 직접 투자한 STR의 {0}% 만큼 DEX 증가")
        this.add(13, "AP를 직접 투자한 DEX의 {0}% 만큼 STR 증가")
        this.add(14, "AP를 직접 투자한 INT의 {0}% 만큼 LUK 증가")
        this.add(15, "AP를 직접 투자한 LUK의 {0}% 만큼 INT 증가")
        this.add(16, "{0} 레벨마다 공격력 1 증가")
        this.add(17, "{0} 레벨마다 마력 1 증가")
        this.add(18, "최대 HP {0}% 증가")
        this.add(19, "최대 MP {0}% 증가")
        this.add(20, "보스 몬스터 공격 시 데미지 {0}% 증가")
        this.add(21, "일반 몬스터 공격 시 데미지 {0}% 증가")
        this.add(22, "상태 이상에 걸린 대상 공격 시 데미지 {0}% 증가")
        this.add(23, "방어력의 {0}% 만큼 데미지 고정값 증가")
        this.add(24, "스킬 사용 시 {0}% 확률로 재사용 대기시간이 미적용")
        this.add(25, "패시브 스킬 레벨 {0} 증가")
        this.add(26, "다수 공격 스킬의 공격 대상 {0} 증가")
        this.add(27, "버프 스킬의 지속 시간 {0}% 증가")
        this.add(28, "아이템 드롭율 {0}% 증가")
        this.add(29, "메소 획득량 {0}% 증가")
        this.add(30, "STR {0}, DEX {1} 증가")
        this.add(31, "STR {0}, INT {1} 증가")
        this.add(32, "STR {0}, LUK {1} 증가")
        this.add(33, "DEX {0}, STR {1} 증가")
        this.add(34, "DEX {0}, INT {1} 증가")
        this.add(35, "DEX {0}, LUK {1} 증가")
        this.add(36, "INT {0}, STR {1} 증가")
        this.add(37, "INT {0}, DEX {1} 증가")
        this.add(38, "INT {0}, LUK {1} 증가")
        this.add(39, "LUK {0}, STR {1} 증가")
        this.add(40, "LUK {0}, DEX {1} 증가")
        this.add(41, "LUK {0}, INT {1} 증가")
    }
}
