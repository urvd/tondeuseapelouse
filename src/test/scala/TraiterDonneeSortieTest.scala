import core.components.{Coordonnee, Pelouse, TondeuseCoordonnee, TondeuseInstruction}
import core.entree.EntryPoints
import core.sortie.Sortie
import org.scalatest.funsuite.AnyFunSuite

class TraiterDonneeSortieTest extends  AnyFunSuite{
  test("on obtient les nouvelles positions a partir des instructions et postions de départ") { //marche
    //given
    var pelouse = new Pelouse(5,5)
    var tondeuses = List(new TondeuseCoordonnee(new Coordonnee(3,3),'E'),
      new TondeuseCoordonnee(new Coordonnee(1,2),'N'))
    var instructions = List(new TondeuseInstruction("AADAADADDA".toList), new TondeuseInstruction("GAGAGAGAA".toList))
    var entries = new EntryPoints()

    //when
    entries.add(pelouse)
    entries.add(tondeuses.lift(0).get)
    entries.add(tondeuses.lift(1).get)
    entries.add(instructions.lift(0).get)
    entries.add(instructions.lift(1).get)

    var output = Sortie(entries)
    var result = output.traitement()
    var listResult = result.tondeusesFin

    //then
    assert(listResult.length == instructions.length)
    assertResult(result.searchTC(0))(new TondeuseCoordonnee(new Coordonnee(4,1),'E'))
    assertResult(result.searchTC(1))(new TondeuseCoordonnee(new Coordonnee(1,3),'N'))
  }
}
