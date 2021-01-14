import core.components.{Coordonnee, Pelouse, TondeuseCoordonnee, TondeuseInstruction}
import core.entree.EntryPoints
import core.generator.JsonGenerator
import core.sortie.{EndPoint, Sortie}
import org.scalatest.funsuite.AnyFunSuite

class GenererResultatFinalTest extends  AnyFunSuite {
  test("on obtient les nouvelles positions des instructions") { //marche
    //given
    var pelouse = new Pelouse(5,5)
    var tondeuses = List(new TondeuseCoordonnee(new Coordonnee(3,3),'E'),
      new TondeuseCoordonnee(new Coordonnee(1,2),'N'))
    var instructions = List(new TondeuseInstruction("AADAADADDA".toList), new TondeuseInstruction("GAGAGAGAA".toList))
    var tondeuses_fin = List(new TondeuseCoordonnee(new Coordonnee(1,3),'N'), new TondeuseCoordonnee(new Coordonnee(4,1),'E'))

    var inputs = new EntryPoints()
    inputs.add(pelouse)
    inputs.add(tondeuses.lift(0).get)
    inputs.add(tondeuses.lift(1).get)
    inputs.add(instructions.lift(0).get)
    inputs.add(instructions.lift(1).get)

    var output = new EndPoint(tondeuses_fin)

    //when
      JsonGenerator.generate(inputs,output)
    //then

  }
}
