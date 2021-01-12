package lecture_specs

import core.components.{Component, Pelouse, TondeuseCoordonnee, TondeuseInstruction}
import core.{DonneesIncorrectesException, Entree, EntryPoints}
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
    assert(entries.list.length == 5)
    assertResult(p)(entries.search(4))
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
