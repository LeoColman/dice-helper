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
grammar DiceNotation ;
@header {
package br.com.colman.dicehelper;
}
notation: dice (operator notation)? EOF;
dice: (amount=DIGIT? D face=DIGIT) | (fixed=DIGIT) ;
operator: '+' | '-' ;

D: 'd' | 'D' ;
DIGIT: [0-9]+ ;
WS: (' ' | [\t\r\n]) -> skip ;