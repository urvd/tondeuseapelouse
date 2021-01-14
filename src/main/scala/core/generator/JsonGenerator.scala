package core.generator


import java.io.{File, PrintWriter}

import core.entree.EntryPoints
import core.generator.Mission
import core.model._
import core.sortie.EndPoint
import play.api.libs.json.{JsValue, Json}

import scala.+:
import scala.io.Source

object JsonGenerator {
  def generate(inputs: EntryPoints, outputs: EndPoint, filename: String = "src/ressources/main/result.json"): Unit = {
    var tondeuses = Array[Tondeuse]()
    var limiteP = inputs.pelouse.lift(0).get
    var limite = new Point(limiteP.x,limiteP.y)
    var count = List.range(0,inputs.instructions.length)
    for(i <- count) yield i match {
      case i => {
        var instrucs = inputs.instructions.reverse.lift(i).get
        var debutTs = inputs.tondeusesDebut.reverse.lift(i).get
        var finTs = outputs.tondeusesFin.lift(i).get

        var arrayInstructions = Array[Char]()
        instrucs.instuctions.map(e => arrayInstructions = arrayInstructions :+  e)

        var new_tondeuse = new Tondeuse(
          new Position(
            new Point(debutTs.coord.x, debutTs.coord.y),
            debutTs.orientation.toString
          ),
          new Instruction(arrayInstructions),
          new Position(
            new Point(finTs.coord.x, finTs.coord.y),
            finTs.orientation.toString
          ),
        )
        tondeuses = tondeuses :+ new_tondeuse
      }
    }
    var result = new Mission(limite, new Tondeuses(tondeuses))
    //print(result.toString())
    val json: JsValue = Json.parse(result.toString)
    //val json2: JsValue = Json.obj(result)
    val writer = new PrintWriter(new File(filename))

    writer.write(json.toString())
    writer.close()
    //Source.fromFile().
    //println(json)
  }
}
