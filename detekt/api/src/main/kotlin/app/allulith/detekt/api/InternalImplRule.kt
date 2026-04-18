package app.allulith.detekt.api

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClassOrObject

class InternalImplRule(config: Config) : Rule(config) {

    override val issue = Issue(
        id = "ImplModulesMustHaveAllFilesInternal",
        severity = Severity.Defect,
        description = "Files in `.impl` packages must be internal.",
        debt = Debt.FIVE_MINS,
    )

    override fun visitKtFile(file: KtFile) {
        val pkg = file.packageFqName.asString()

        val insideImplPackage = pkg.split('.').contains("impl")
        if (!insideImplPackage) return

        file.declarations.forEach { declaration ->
            when (declaration) {
                is KtNamedFunction -> checkFunction(declaration)
                is KtClassOrObject -> checkClassOrObject(declaration)
            }
        }
    }

    private fun checkClassOrObject(classOrObject: KtClassOrObject) {
        classOrObject.declarations.forEach { member ->
            if (member is KtNamedFunction) {
                checkFunction(member)
            }
        }
    }

    private fun checkFunction(function: KtNamedFunction) {
        val isInternal = function.hasModifier(KtTokens.INTERNAL_KEYWORD)
        val isPrivate = function.hasModifier(KtTokens.PRIVATE_KEYWORD)

        if (!isInternal && !isPrivate) {
            report(
                CodeSmell(
                    issue = issue,
                    entity = Entity.from(function),
                    message = "Function `${function.name}` must be internal because it is in a `.impl` package.",
                )
            )
        }
    }
}
