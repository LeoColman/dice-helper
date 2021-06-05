# Dice Helper

[![Build](https://github.com/LeoColman/dice-helper/actions/workflows/build.yml/badge.svg)](https://github.com/LeoColman/dice-helper/actions/workflows/build.yml)
[![GitHub](https://img.shields.io/github/license/LeoColman/dice-helper)](https://github.com/LeoColman/dice-helper/blob/master/LICENSE) 
[![Maven Central](https://img.shields.io/maven-central/v/group/dicee-helper.svg)](https://search.maven.org/search?q=g:group)

A Kotlin library to help you parse DiceNotations and roll dice for your RPG games.

# Using
Adding this library to your project is pretty simple, just add it to your gradle `dependencies`:

```kotlin
repositories {
    mavenCentral()
}
dependencies {
    implementation("br.com.colman:dice-helper:version")
}
```

# Dice Notation
This library tries to implement a Dice Notation as it's commonly used. More use cases will be added as they're needed.

You can understand a little bit more of Dice Notation by reading [this Wikipedia article](https://en.wikipedia.org/wiki/Dice_notation).

# Features

Parse dice notation (currently only supports `+` and `-`)
```kotlin
val diceNotation: DiceNotation = "5d6 + 12 - 3d5".diceNotation()
```

Roll all dice/fixed values in a dice notation
```kotlin
val result: NotationRollResult = diceNotation.roll()
val total = result.total
val individualResults: List<DiceRollResult> = result.results
```

Roll a group of dice
```kotlin
// 15d10
val rollResult: List<Int> = RandomDice(amount = 15, maxFaceValue = 10).roll()
val total = rollResult.sum()
```

# Contributing
Please, feel free to create an issue or to open a pull request! I'll be glad to have your help :)