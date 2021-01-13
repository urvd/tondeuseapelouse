package core.sortie

import core.components.TondeuseCoordonnee

class EndPoint(var tondeusesFin: List[TondeuseCoordonnee]){
  def searchTC(index: Int): TondeuseCoordonnee = {
    tondeusesFin.lift(index).get
  }

  def show(): Unit ={
    var i = 0
    for( e <- tondeusesFin){
      println(i + ": "+ e.coord.x + " " + e.coord.y + " " + e.orientation)
      i = i + 1
    }
  }
}
