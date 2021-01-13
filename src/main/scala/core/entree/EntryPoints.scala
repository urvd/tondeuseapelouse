package core.entree

import core.components._
import core.exception.{DonneesIncorrectesException, DonneesIncorrectesExceptionValidation}

import scala.io.Source

class EntryPoints(var pelouse: List[Pelouse], var tondeusesDebut: List[TondeuseCoordonnee], var instructions: List[TondeuseInstruction]) {
  def this() = this(List(), List(), List())
  def add(a:ComponentImpl):Unit = a match {
    case p:Pelouse => pelouse = p::pelouse
    case tc:TondeuseCoordonnee => tondeusesDebut = tc::tondeusesDebut
    case ti:TondeuseInstruction => instructions = ti::instructions
  }
  def searchP(index: Int = 1): Pelouse = {
    pelouse.head
  }
  def searchTC(index: Int): TondeuseCoordonnee = {
    tondeusesDebut.lift(index).get
  }
  def searchTI(index: Int): TondeuseInstruction = {
    instructions.lift(index).get
  }
}

