package app.allulith.detekt.api

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class OrganiserRuleSetProvider : RuleSetProvider {
    override val ruleSetId: String = "organiser"

    override fun instance(config: Config): RuleSet {
        return RuleSet(
            ruleSetId,
            listOf(
                InternalImplRule(config)
            )
        )
    }
}
