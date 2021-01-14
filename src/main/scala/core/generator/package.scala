package core

import core.json_maker._
import core.model.{Tondeuses, _}

package object generator {

  object Mission {
    //def apply(limite: Point, tondeuses: Tondeuses):Mission = new Mission(limite, tondeuses)
    implicit def writesMission():Writes[Mission] = new Writes[Mission]() {
      override def writes(value: Mission): JsValue = JsObject(Map(
        ("limite", Point.writesPoint.writes(value.limite)),
        ("tondeuses", Tondeuses.writesTondeuses.writes(value.tondeuses))
      ))
    }
  }

  object Tondeuses {
    implicit def writesTondeuses():Writes[Tondeuses] = new Writes[Tondeuses]() {
      override def writes(value: Tondeuses): JsValue = JsArray(
        value.array.map( arr => {
          Tondeuse.writesTondeuse.writes(arr)
        })
      )
    }
  }
  object Tondeuse {
    implicit def writesTondeuse():Writes[Tondeuse] = new Writes[Tondeuse]() {
      override def writes(value: Tondeuse):JsValue = JsObject(Map(
        ("debut", Position.writesPosition.writes(value.debut)),
        ("instructions", Instruction.writesInstruction.writes(value.instruction)),
        ("fin", Position.writesPosition.writes(value.fin))
      ))
    }
  }
  object Position {
    implicit def writesPosition():Writes[Position] = new Writes[Position]() {
      override def writes(value: Position): JsValue = JsObject(Map(
        ("point", Point.writesPoint.writes(value.point)),
        ("direction", Writes.writesString.writes(value.direction))
      ))
    }
  }
  object Instruction {
    implicit def writesInstruction():Writes[Instruction] = new Writes[Instruction]() {
      override def writes(value: Instruction): JsValue = JsArray(
        value.array.map(arr => Writes.writesString.writes(arr.toString))
      )
    }
  }
  object Point{
    implicit def writesPoint():Writes[Point]  = new Writes[Point]() {
      override def writes(value: Point): JsValue = JsObject(Map(
        ("x", Writes.writesInt.writes(value.x)),
        ("y", Writes.writesInt.writes(value.y))
      ))
    }
  }
}
