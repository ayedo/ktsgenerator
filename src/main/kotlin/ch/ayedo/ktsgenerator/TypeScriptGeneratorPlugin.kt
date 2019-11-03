package ch.ayedo.ktsgenerator

import org.gradle.api.Plugin
import org.gradle.api.Project


open class TypeScriptGeneratorPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        project.extensions.create("typescriptGenerator", TypeScriptGeneratorExtension::class.java)

        project.afterEvaluate({

            val config = project.extensions.findByType(TypeScriptGeneratorExtension::class.java)!!

            project.tasks.create("generateTypescriptDefinitions", TypeScriptGeneratorTask::class.java).apply {
                description = "Generates Typescript definitions from Kotlin classes."
                outputPath = config.outputPath ?: throw IncompletePluginConfigurationException(
                    "outputPath"
                )
                classPath = config.classPath ?: throw IncompletePluginConfigurationException(
                    "classPath"
                )
                packageName = config.packageName ?: throw IncompletePluginConfigurationException(
                    "packageName"
                )
                typeMappings = config.typeMappings ?: throw IncompletePluginConfigurationException(
                    "typeMappings"
                )
                postfixFilters = config.postfixFilters ?: throw IncompletePluginConfigurationException(
                    "postfixFilters"
                )
                imports = config.imports ?: throw IncompletePluginConfigurationException(
                    "imports"
                )
                intTypeName = config.intTypeName ?: throw IncompletePluginConfigurationException(
                    "intTypeName"
                )
                voidType = config.voidType ?: throw IncompletePluginConfigurationException(
                    "voidType"
                )
            }

        })

    }

    class IncompletePluginConfigurationException(missing: String) : IllegalArgumentException(
        "Incomplete TypescriptGenerator plugin configuration: $missing is missing"
    )

}
