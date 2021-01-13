package core

import core.components.{Component, Coordonnee, Pelouse, TondeuseCoordonnee, TondeuseInstruction}
import core.exception.{DonneesIncorrectesException, DonneesIncorrectesExceptionValidation}

import scala.io.Source

package object entree {

  object Entree {
    def apply(filename: String): Entree = new Entree(filename)
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
            val ep_tondCoord = new TondeuseCoordonnee(
              new Coordonnee(Integer.parseInt(line.charAt(0).toString),
                Integer.parseInt(line.charAt(2).toString)),
              line.charAt(4))
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
}
