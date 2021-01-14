import core.components.{Coordonnee, Pelouse, TondeuseCoordonnee, TondeuseInstruction}
import core.entree.EntryPoints
import core.sortie.EndPoint
import play.api.libs.json.Json

package object mock_data {
  object MockedData {
    val JSON_Result_MOCK = Json.stringify(
      Json.parse(
        """{"limite":{"x":5,"y":5},"tondeuses":[{"debut":{"point":{"x":1,"y":2},"direction":"N"},
          |"instruction":["G","A","G","A","G","A","G","A","A"],"fin":{"point":{"x":1,"y":3},"direction":"N"}},
          |{"debut":{"point":{"x":3,"y":3},"direction":"E"},"instruction":["A","A","D","A","A","D","A","D","D","A"],
          |"fin":{"point":{"x":4,"y":1},"direction":"E"}}]}"""))

    def ENTRYPOINT_MOCK:EntryPoints = {
      var pelouse = new Pelouse(5,5)
      var tondeuses = List(new TondeuseCoordonnee(new Coordonnee(3,3),'E'),
        new TondeuseCoordonnee(new Coordonnee(1,2),'N'))
      var instructions = List(new TondeuseInstruction("AADAADADDA".toList), new TondeuseInstruction("GAGAGAGAA".toList))

      new EntryPoints(List(pelouse),tondeuses,instructions)
    }

    def ENDPOINT_MOCK:EndPoint = {
      new EndPoint(List(new TondeuseCoordonnee(new Coordonnee(1,3),'N'),
        new TondeuseCoordonnee(new Coordonnee(4,1),'E')))
    }
  }
}
