import core.components.{Coordonnee, Pelouse, TondeuseCoordonnee, TondeuseInstruction}
import core.entree.EntryPoints
import core.sortie.{EndPoint, Sortie}
import mock_data.MockedData.{ENDPOINT_MOCK, ENTRYPOINT_MOCK}
import org.scalatest.funsuite.AnyFunSuite

class TraiterDonneeSortieTest extends AnyFunSuite{


  def ENTRYPOINT_MOCK:EntryPoints = {
    var pelouse = new Pelouse(5,5)
    var tondeuses = List(new TondeuseCoordonnee(new Coordonnee(3,3),'E'),
      new TondeuseCoordonnee(new Coordonnee(1,2),'N'))
    var instructions = List(new TondeuseInstruction("AADAADADDA".toList), new TondeuseInstruction("GAGAGAGAA".toList))

    new EntryPoints(List(pelouse),tondeuses,instructions)
  }

  def ENDPOINT_MOCK:EndPoint = {
    new EndPoint(List(new TondeuseCoordonnee(new Coordonnee(1,3),'N'),
      new TondeuseCoordonnee(new Coordonnee(4,1),'E')).reverse)
  }

  test("on obtient les nouvelles positions a partir des instructions et postions de départ") { //marche
    //given
    var pelouse = ENTRYPOINT_MOCK.pelouse.lift(0).get
    var tondeuses = ENTRYPOINT_MOCK.tondeusesDebut
    var instructions = ENTRYPOINT_MOCK.instructions
    var tondeuses_fin = ENDPOINT_MOCK.tondeusesFin

    //when
    var output = Sortie(ENTRYPOINT_MOCK)
    var result = output.traitement()
    var listResult = result.tondeusesFin

    //then
    assert(listResult.length == instructions.length)
    assertResult(result.searchTC(1))(new TondeuseCoordonnee(new Coordonnee(4,1),'E'))
    assertResult(result.searchTC(0))(new TondeuseCoordonnee(new Coordonnee(1,3),'N'))
  }
}
