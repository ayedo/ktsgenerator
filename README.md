# ktsgenerator

This gradle plugin generates Typescript definitions from Kotlin classes.

## Installation

Add the following to your *build.gradle* plugin configuration block:

    plugins {
          id 'ch.ayedo.ktsgenerator' version '2.0.0'
    }
    
## Configuration

Add the following to your *build.gradle* file:

    typescriptGenerator {
    
      // The path to the resulting generated type defintions file
      outputPath = project.projectDir.toPath().resolve("src/typings/typings.d.ts") // required, this is an example
    
      // The classpath to scan for classes to convert to typescript defintions
      classPath = layout.files(
        project(":project-name").sourceSets.main.runtimeClasspath) // required, this is an example
        
      // Only Kotlin classes from this package will be considered for generation
      packageName = "com.example.backend" // required, this is an example

      // Only classes which have the follwing postfixes will be generated
      postfixFilters = ["OP", "IP"] // optional, the default is []

      // Which type to use for int
      intTypeName = "number" // optional, this is the default
    
      // Custom type mappings
      typeMappings = [
        "java.time.LocalDateTime": 'Moment',
        "java.time.LocalDate"    : 'DateMoment',
        "java.time.LocalTime"    : 'TimeMoment',
        "java.util.UUID"         : 'string',
        "java.net.URL"           : 'string',
        "java.math.BigDecimal"   : 'BigNumber'] // optional, the default is []
      
      // Imports to be added at the top of the generated typescript defintions file
      imports = ["import { Moment } from 'moment';",
                 "import BigNumber from 'bignumber.js';"] // optional, the default is []
    
      // What to use for void type. 
      // Options: 'NULL', or 'UNDEFINED'
      voidType = "UNDEFINED" // optional, this is the default
    
    }
    
## Usage

The plugins adds a task named *generateTypescriptDefinitions* to your build.

Use it to generate the typescript definitions file:

    ./gradlew generateTypescriptDefinitions
    
## Acknowledgments

This plugin uses [ts-generator](https://github.com/ntrrgc/ts-generator).

