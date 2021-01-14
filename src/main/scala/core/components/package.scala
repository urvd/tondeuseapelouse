package core

package object components {
  trait Component {
    def equals(other: Any): Boolean
  }

  class ComponentImpl() extends Component {
    override def equals(other: Any): Boolean = other match {
      case p: ComponentImpl => true
      case _ => false
    }
  }

  // class pour les stocker la donnée sur la pelouse
  case class Pelouse(x: Int, y: Int) extends ComponentImpl {
    override def equals(other: Any): Boolean = other match {
      case p: Pelouse => p.x == this.x && p.y == this.y
      case _ => false
    }
  }

  // class pur les coordonnee les donnée(s) sur (la/les) Tondeuse(s)
  case class TondeuseCoordonnee(coord: Coordonnee, orientation: Char) extends ComponentImpl {
    override def equals(other: Any): Boolean = other match {
      case tc: TondeuseCoordonnee => this.coord.equals(tc.coord) && this.orientation.equals(tc.orientation)
      case _ => false
    }
  }

  // class pur les instruction les donnée(s) sur (la/les) Tondeuse(s)
  class TondeuseInstruction(val instuctions: List[Char]) extends ComponentImpl {
    override def equals(other: Any): Boolean = other match {
      case ti: TondeuseInstruction => this.instuctions == ti.instuctions
      case _ => false
    }
  }

  class Coordonnee(var x: Int, var y: Int) extends Component {
    override def equals(other: Any): Boolean = other match {
      case cr: Coordonnee => cr.x == this.x && cr.y == this.y
      case _ => false
    }
  }

}
