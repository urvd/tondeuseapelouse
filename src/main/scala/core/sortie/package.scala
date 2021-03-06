package core

import core.entree.EntryPoints
import core.traitement.Traitement
import  core.components._

import scala.annotation.tailrec

package object sortie {
  object Sortie {
    def apply(entryPoints: EntryPoints): Sortie = new Sortie(entryPoints)
    def reponse(entryPoints: EntryPoints):EndPoint = {
      new Sortie(entryPoints).traitement()
    }
  }

  class Sortie(entryPoints: EntryPoints){
    def traitement():EndPoint = {
      var pelouse = entryPoints.pelouse.head
      var instructions = entryPoints.instructions.reverse
      var tondeuses = entryPoints.tondeusesDebut.reverse

      @tailrec
      def helperRec(tondeusesI: List[TondeuseInstruction], tondeusesC: List[TondeuseCoordonnee],
                    add_tondeuses_final: List[TondeuseCoordonnee]): List[TondeuseCoordonnee] = (tondeusesI,tondeusesC) match {
        case (Nil, Nil) => {
          add_tondeuses_final
        }
        case (headI::tailI, headC::tailC) =>{
          var traitement = Traitement(pelouse.x, pelouse.y)
          var tf = traitement.fin(headC.orientation, headC.coord, headI.instuctions)
          var newlist:List[TondeuseCoordonnee] = tf::add_tondeuses_final
          helperRec(tailI, tailC, newlist)
        }
      }
      new EndPoint(helperRec( instructions, tondeuses, Nil).reverse)

    }
  }
}
