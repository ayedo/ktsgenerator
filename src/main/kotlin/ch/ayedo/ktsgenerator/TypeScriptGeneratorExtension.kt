package ch.ayedo.ktsgenerator

import me.ntrrgc.tsGenerator.VoidType
import org.gradle.api.file.FileCollection
import java.nio.file.Path

open class TypeScriptGeneratorExtension {
    var outputPath: Path? = null
    var classPath: FileCollection? = null
    var packageName: String? = null
    var typeMappings: Map<String, String>? = null
    var postfixFilters: List<String>? = null
    var imports: List<String> = listOf()
    var intTypeName: String = "number"
    var voidType: String = "UNDEFINED"
}
