# ktsgenerator

This gradle plugin generates Typescript definitions from Kotlin classes.

## Installation

Add the following to your *build.gradle*:

    buildscript {

        repositories {
            maven { url 'https://jitpack.io' }
        }

        dependencies {
            classpath "com.github.ntrrgc:ts-generator:1.1.1"
        }
    }

    plugins {
          id 'ch.ayedo.ktsgenerator' version '2.1.0'
    }
    
## Configuration

To configure the plugin you'll have to add a `typescriptGenerator` block to your `build.gradle` file.

For an example project please see section *"Example Project"*.

### Minimal Configuration

For a minimal configuration add the following to your `build.gradle` file:

    typescriptGenerator {
    
      // The path to the resulting generated type defintions file
      outputPath = project.projectDir.toPath().resolve("src/typings/typings.d.ts") // required, this is an example
    
      // The classpath to scan for classes to convert to typescript defintions
      classPath = layout.files(getProject().sourceSets.main.runtimeClasspath) // required, this is an example
        
      // Only Kotlin classes from this package will be considered for generation
      packageName = "com.example.backend" // required, this is an example
    
    }

### Filtering Classes by Postfix

You can further filter the classes in the stated package name by a postfix:

    postfixFilters = ["OP", "IP"] // optional, the default is []

In this example, only classes which end in "OP", or "IP" will be included.

### Custom Type Mappings

You can configure how the Kotlin/Java types should be mapped onto Typescript types

    typeMappings = [
        "java.time.LocalDateTime": 'Moment',
        "java.time.LocalDate"    : 'DateMoment',
        "java.time.LocalTime"    : 'TimeMoment',
        "java.util.UUID"         : 'string',
        "java.net.URL"           : 'string',
        "java.math.BigDecimal"   : 'BigNumber'] // optional, the default is [] 

In this example every field of type `java.time.LocalDateTime` in the Kotlin class will be translated to a field of type `Moment` in typescript. The other mappings follow analogously. 

### Imports

The result of this generator plugin is a single typescript definitions file. If you use non-standard types in your type mappings, they might require import statements at the top of the resulting file. You can add them like so:

    imports = ["import { Moment } from 'moment';",
             "import BigNumber from 'bignumber.js';"] // optional, the default is []
       
### Void Type

You can decide what should be generated for the void type:

    // Options: 'NULL', or 'UNDEFINED'
    voidType = "UNDEFINED" // optional, this is the default

### Int Type

You can configure which type to use for the int type:

    intTypeName = "number" // optional, this is the default
            
## Example Project

[Click here to see an example project.](https://github.com/ayedo/ktsgenerator-example)

## Usage

The plugins adds a task named *generateTypescriptDefinitions* to your build.

Use it to generate the typescript definitions file:

    ./gradlew generateTypescriptDefinitions
    
## Acknowledgments

This plugin uses [ts-generator](https://github.com/ntrrgc/ts-generator).

