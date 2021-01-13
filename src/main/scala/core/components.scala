package core

import core.components.{ComponentImpl, Pelouse}

object components {

  trait Component {
    def equals(other: Any): Boolean
  }
//  object Pelou{
//    def apply[A](cp:A):CurrentComponent[A] = new CurrentComponent[A](cp:A)
//  }
  class ComponentImpl() extends Component {

    override def equals(other: Any): Boolean = other match{
      case p: ComponentImpl => true
      case _=> false
    }
//    def create(p:Pelouse): Pelouse = {
//      new Pelouse()
//    }
  }
  case class Pelouse(x: Int, y: Int) extends ComponentImpl{

     def this() = this(0,0)

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
    //val getOrientation:Char = this.orientation
  }

  class TondeuseInstruction(val instuctions: List[Char]) extends ComponentImpl{
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
    def setXY(x:Int,y:Int){
      this.x = x
      this.y = y
    }
    def setX(x:Int){
      this.x = x
    }
    def setY(y:Int){
      this.y = y
    }

  }

}
