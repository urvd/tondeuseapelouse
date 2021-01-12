package core

import core.components.{Component, ComponentImpl, Coordonnee, Pelouse, TondeuseCoordonnee, TondeuseInstruction}

import scala.annotation.tailrec
import scala.io.Source



object Entree {
  def apply(filename: String): Entree = new Entree(filename)
}

class EntryPoints(var list: List[Component]) {
  def this() = this(List())
  def add(a:Component):Unit = a match {
    case p:Pelouse => list = a::list
    case tc:TondeuseCoordonnee => list = tc::list
    case ti:TondeuseInstruction => list = ti::list
  }

  def search(index: Int): ComponentImpl = {
    var component = new ComponentImpl()
    for (a <- list if(a == list.lift(index).get)) yield a match {
      case p:Pelouse => component = p.copy()
      case tc:TondeuseCoordonnee => component = tc.copy()
      case ti:TondeuseInstruction => component = ti.copy()
    }
    component
  }
}

class Entree(filename: String) {

  def lecture(): EntryPoints = {
    var iterator: Iterator[String] = Source.fromFile(filename).getLines()
    var entries:EntryPoints = new EntryPoints()
    val valid = new DonneesIncorrectesExceptionValidation()
    try {
      var nb = iterator.length
      valid.globalEntries(nb)
    } catch {
      case e:DonneesIncorrectesException[Component] => throw e
    }
    var acc = 0
    Source.fromFile(filename).getLines().foreach(line => {
          println(acc + ": " + line)
          if (acc == 0) {
            try {
              valid.pelouseEntries(line, acc)
              val ep_pelouse = new Pelouse(Integer.parseInt(line.charAt(0).toString),
                Integer.parseInt(line.charAt(2).toString))
              entries.add(ep_pelouse)
            } catch {
              case e: DonneesIncorrectesException[Pelouse] => throw e
            }
          }else if(acc % 2 != 0) {
            try {
              valid.tondeuseCoordonneeEntries(line, acc)
              val ep_tondCoord = new TondeuseCoordonnee(new Coordonnee(line.charAt(0).toInt,
                line.charAt(2).toInt), line.charAt(4))
              entries.add(ep_tondCoord)
            } catch {
              case e: DonneesIncorrectesException[TondeuseCoordonnee] => throw e
            }
          } else if (acc % 2 == 0 && acc != 0) {
              try {
                valid.tondeuseInstructionEntries(line, acc)
                val ep_tondInstruc = new TondeuseInstruction(line.toList)
                entries.add(ep_tondInstruc)
              } catch {
                case e: DonneesIncorrectesException[TondeuseInstruction] => throw e
              }
          }
          acc = acc + 1
    })
    entries
  }
}