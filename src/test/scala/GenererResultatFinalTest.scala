import core.components._
import core.entree.EntryPoints
import core.generator.GeneratorToJson
import core.sortie.EndPoint
import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source
import scala.reflect.io.File

class GenererResultatFinalTest extends  AnyFunSuite {

  val JSON_STRING_Result_MOCK =
    """{"limite":{"x":5,"y":5},"tondeuses":[{"debut":{"point":{"x":1,"y":2},"direction":"N"},"instruction":["A","A","D","A","A","D","A","D","D","A"],"fin":{"point":{"x":1,"y":3},"direction":"N"}},{"debut":{"point":{"x":3,"y":3},"direction":"E"},"instruction":["G","A","G","A","G","A","G","A","A"],"fin":{"point":{"x":4,"y":1},"direction":"E"}}]}""".stripMargin
  def ENTRYPOINT_MOCK:EntryPoints = {
    var pelouse = new Pelouse(5,5)
    var tondeuses = List(new TondeuseCoordonnee(new Coordonnee(3,3),'E'),
      new TondeuseCoordonnee(new Coordonnee(1,2),'N'))
    var instructions = List(new TondeuseInstruction("GAGAGAGAA".toList), new TondeuseInstruction("AADAADADDA".toList))

    new EntryPoints(List(pelouse),tondeuses,instructions)
  }

  def ENDPOINT_MOCK:EndPoint = {
    new EndPoint(List(new TondeuseCoordonnee(new Coordonnee(1,3),'N'),
      new TondeuseCoordonnee(new Coordonnee(4,1),'E')))
  }

  test("on genere un fichier json contenant les info entrée et de sortie") { //marche
    //given
    var FILENAME_RESULT = "src/ressources/test/result_test.json"
    var inputs = ENTRYPOINT_MOCK
    var output = ENDPOINT_MOCK

    //when
    GeneratorToJson.generate(inputs, output, filename = FILENAME_RESULT)
    //then

    assert(File(FILENAME_RESULT).exists)
    var str = ""
    Source.fromFile(FILENAME_RESULT).getLines().foreach(e => {
      str = str + e
    })

    assert(str.length == JSON_STRING_Result_MOCK.length)
    assert(str.equals(JSON_STRING_Result_MOCK))
  }
}
