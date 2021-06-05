/*
 * Copyright 2021 Leonardo Colman Lopes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.colman.dicehelper


import org.antlr.v4.runtime.CharStreams.fromString
import org.antlr.v4.runtime.CommonTokenStream

/**
 * Returns all dice in a dice notation ignoring operations
 *
 * Useful when you're rolling simple dice that doesn't require notation evaluation. Usually followed by [rollAll] to
 * obtain results.
 *
 * "17d4".dice() // Returns listOf(RandomDice(amount = 17, mqxFaceValue = 4))
 */
public fun String.dice(): List<Dice> = diceNotation().allDice

/**
 * Parses a dice notation
 *
 * Useful to parse more complex dice notation. Usually followed by [DiceNotation.roll] to roll all the dice in the
 * notation and evaluate the expression to find the final result.
 *
 * "5d4 + 2d5 - 10".diceNotation().roll()   // Work with results
 */
public fun String.diceNotation(): DiceNotation = DiceNotation(diceNotationParser.notation())

private val String.diceNotationParser
    get() = DiceNotationParser(CommonTokenStream(DiceNotationLexer(fromString(this))))
