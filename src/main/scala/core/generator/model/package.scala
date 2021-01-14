package core

import core.generator.{Point, Tondeuses}
import core.json_maker.{JsNumber, JsString}

package object model {

  case class Mission(limite: Point, tondeuses: Tondeuses) extends Serializable{
//    //def this(limite: Point, tondeuses: Tondeuses) = this(limite, tondeuses)
    override def toString: String = {
       "{\"limite\" :" + limite + ",\n" + "\"tondeuses\" :" + tondeuses.toString + "}"
    }
  }

  case class Tondeuses(array: Array[Tondeuse]) extends Serializable{
    override def toString: String = {
      var str = "["
      for(i <- 0 to array.length - 1){

        if(i == array.length-1){
          str = str + array(i).toString
        }else str = str + array(i).toString + ","
      }
      str + "]"
    }
  }
  case class Tondeuse(debut:Position, instruction: Instruction, fin:Position) extends Serializable{
    override def toString: String = {
      "{\"debut\" :" + debut.toString + "," + "\"instruction\" :" + instruction.toString + "," +
        "\"fin\" :" + fin.toString + "}"
    }
  }
  case class Position(point: Point, direction: String) extends Serializable{
    override def toString: String = {
      "{\"point\" :" + point.toString + "," +
        "\"direction\" :" + "\"" + direction + "\"" + "}"
    }
  }
  case class Instruction(array: Array[Char]) extends Serializable{
    override def toString: String = {
      var str = "\n["
      for(i <- 0 to array.length - 1){
        if(array.length - 1 == i){
          str = str + "\"" + array(i) + "\""
        }else str = str + "\"" + array(i) + "\"" + ","
      }
      str + "]"
    }
  }
  case class Point(x: Int, y: Int) extends Serializable{
    override def toString: String = {
      "{\"x\" :" + x + "," + "\"y\" :" +  y  + "}"
    }
  }
}
