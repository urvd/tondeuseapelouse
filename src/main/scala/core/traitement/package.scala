package core

import core.components.{Coordonnee, TondeuseCoordonnee}

import scala.annotation.tailrec

package object traitement {
  object Traitement {
    def apply(x_size: Int, y_size: Int): Traitement = new Traitement(x_size, y_size)
  }

  class Traitement(val x_size: Int, val y_size: Int) {

    def fin(start: Char, tondeuse_position: Coordonnee, instructionsT: List[Char]): TondeuseCoordonnee = {
      @tailrec
      def traiterHelper(instructions: List[Char], tondeuse: TondeuseCoordonnee):TondeuseCoordonnee = instructions match {
        case Nil => tondeuse
        case head::tail if(head == 'A') => {
          var new_position = deplacer(tondeuse.coord.x, tondeuse.coord.y, tondeuse.orientation)
          var new_tondeuse = tondeuse.copy(coord = new_position)
          traiterHelper(tail, new_tondeuse)
        }
        case  head::tail if(head == 'D') => {
          var new_pivot = orienter(tondeuse.orientation, head)
          var new_tondeuse = tondeuse.copy(orientation=new_pivot)
          traiterHelper(tail, new_tondeuse)
        }
        case head::tail if(head == 'G') => {
          var new_pivot = orienter(tondeuse.orientation, head)
          var new_tondeuse = tondeuse.copy(orientation=new_pivot)
          traiterHelper(tail, new_tondeuse)
        }
      }
      traiterHelper(instructionsT,new TondeuseCoordonnee(tondeuse_position, start))
    }

    private def deplacer(x: Int, y: Int, orientation: Char):Coordonnee = (x, y, orientation) match {
      case (x, y,'N') => {
        if(y == y_size - 1) new Coordonnee(x, y)
        else new Coordonnee(x, y + 1)
      }
      case (x, y,'S') => {
        if(y == 0) new Coordonnee(x, y)
        else new Coordonnee(x, y - 1)
      }
      case (x, y,'E') => {
        if(x == x_size - 1) new Coordonnee(x, y)
        else new Coordonnee(x+1,y)
      }
      case (x, y,'O') => {
        if(x == 0) new Coordonnee(x, y)
        else new Coordonnee(x-1,y)
      }
    }

    private def orienter(direction_start: Char, sens_deplacement:Char): Char = (direction_start, sens_deplacement) match {
      case ('N','G') => 'O'
      case ('N','D') => 'E'
      case ('S','G') => 'E'
      case ('S','D') => 'O'
      case ('O','G') => 'S'
      case ('O','D') => 'N'
      case ('E','G') => 'N'
      case ('E','D') => 'S'
    }
  }
}
