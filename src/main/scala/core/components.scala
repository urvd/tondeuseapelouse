package core

object components {

  trait Component

  case class Pelouse(x: Int, y: Int) extends Component{}

  //case class Tondeuse

  case class TondeuseCoordonnee(coord: Coordonnee, orientation: Char) extends Component

  case class TondeuseInstruction(instuctions: List[Char]) extends Component

  case class Coordonnee(x: Int, y: Int)
}
