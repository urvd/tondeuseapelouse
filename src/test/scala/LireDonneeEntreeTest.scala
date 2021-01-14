import core.components.{Component, Pelouse, TondeuseCoordonnee, TondeuseInstruction}
import core.entree.{Entree, EntryPoints}
import core.exception.DonneesIncorrectesException
import org.scalatest.funsuite.AnyFunSuite

class LireDonneeEntreeTest extends AnyFunSuite {
  var file_root = "src/ressources/test/"
  test("on recupere les données d'entree sans erreur") { //marche
    //given
    var filename = file_root + "entries_correct.txt"
    var p = new Pelouse(5,5)

    //when
    var input =  Entree(filename)
    var entries: EntryPoints = input.lecture()

    //then
    var tcs = entries.tondeusesDebut.reverse
    var tis = entries.instructions.reverse
    assert(tis.length == 2 && tcs.length == 2)
    assertResult(p)(entries.searchP())

    assertResult(tcs.lift(0).get)(entries.searchTC(1))
    assertResult(tcs.lift(1).get)(entries.searchTC(0))
    assertResult(tis.lift(0).get)(entries.searchTI(1))
    assertResult(tis.lift(1).get)(entries.searchTI(0))
  }

  test("on ne recupere pas les données d'entree car elle sont vide") { //marche
    var filename = file_root + "entries_empty.txt"
    var input =  Entree(filename)
    assertThrows[DonneesIncorrectesException[Component]](input.lecture())
  }

  test("on ne recupere pas les données d'entree sur les pelouses a cause de la syntaxe") { //marche
    var filename = file_root + "entries_pelouse_uncorrect.txt"
    var input =  Entree(filename)
    assertThrows[DonneesIncorrectesException[Pelouse]](input.lecture())
  }

  test("on ne recupere pas les données d'entree sur les tondeuses a cause de la syntaxe") { //marche
    var filename = file_root + "entries_tondeuse_uncorrect.txt"
    var input =  Entree(filename)
    assertThrows[DonneesIncorrectesException[TondeuseCoordonnee]](input.lecture())
  }

  test("on ne recupere pas les données d'entree sur les tondeuses a cause des données incoherent") { //marche
    var filename = file_root + "entries_tondeuse2_uncorrect.txt"
    var input =  Entree(filename)
    assertThrows[DonneesIncorrectesException[TondeuseInstruction]](input.lecture())
  }
}
