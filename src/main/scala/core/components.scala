package core

import core.components.Pelouse

object components {

  trait Component {
    def equals(other: Any): Boolean
  }

  class ComponentImpl() extends Component {
    override def equals(other: Any): Boolean = other match{
      case p: ComponentImpl => true
      case _=> false
    }
  }
  case class Pelouse(x: Int, y: Int) extends ComponentImpl{
     override def equals(other: Any): Boolean = other match {
       case p: Pelouse => p.x == this.x && p.y == this.y
       case _=> false
     }
  }

  //case class Tondeuse

  case class TondeuseCoordonnee(coord: Coordonnee, orientation: Char) extends ComponentImpl{
    override def equals(other: Any): Boolean = other match {
      case tc: TondeuseCoordonnee => this.coord.equals(tc.coord) && this.orientation.equals(tc.orientation)
      case _=> false
    }
  }

  case class TondeuseInstruction(instuctions: List[Char]) extends ComponentImpl{
    override def equals(other: Any): Boolean = other match {
      case ti: TondeuseInstruction => this.instuctions == ti.instuctions
      case _=> false
    }
  }

  class Coordonnee(var x: Int, var y: Int) extends Component {
    override def equals(other: Any): Boolean = other match {
      case cr: Coordonnee => cr.x == this.x && cr.y == this.y
      case _=> false
    }
  }
}
