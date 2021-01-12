package core

import java.beans.Beans.isInstanceOf

import core.components.{Component, Pelouse, TondeuseCoordonnee, TondeuseInstruction}

class DonneesIncorrectesException[A](s:String) extends Exception(s)

class DonneesIncorrectesExceptionValidation {
  @throws(classOf[DonneesIncorrectesException[Component]])
  def globalEntries(entrieslines: Int): Unit = {
    if (entrieslines < 3 || entrieslines % 2 == 0) {
      throw new DonneesIncorrectesException(ErrorConst.ERROR_data_lenght(entrieslines))
    }
  }
  @throws(classOf[DonneesIncorrectesException[Pelouse]])
  def pelouseEntries(string: String, emplacement: Int): Unit = {
    if (emplacement != 0) {
      throw new DonneesIncorrectesException[Pelouse](ErrorConst.ERROR_Pelouse_Position)
    } else {
      if (string.length() != 3) {
        throw new DonneesIncorrectesException[Pelouse](ErrorConst.ERROR_Pelouse_lenght)
      } else {
        if (!string.charAt(0).toInt.isInstanceOf[Int]) {
          throw new DonneesIncorrectesException[Pelouse](ErrorConst.ERROR_Pelouse_type_value(0))
        }
        if (!string.charAt(2).toInt.isInstanceOf[Int]) {
          throw new DonneesIncorrectesException[Pelouse](ErrorConst.ERROR_Pelouse_type_value(2))
        }
      }
    }
  }
    @throws(classOf[DonneesIncorrectesException[TondeuseCoordonnee]])
    def tondeuseCoordonneeEntries(string: String, emplacement: Int){
      if(emplacement%2 == 0){
        throw new DonneesIncorrectesException(ErrorConst.ERROR_Tondeuse_coord_emplacement)
      }else{
        if(string.length() != 5){
          throw new DonneesIncorrectesException(ErrorConst.ERROR_Pelouse_lenght)
        }else{
          if(!string.charAt(0).toInt.isInstanceOf[Int]) {
            throw new DonneesIncorrectesException(ErrorConst.ERROR_Tondeuse_coord_type_value(0))
          }
          if(!string.charAt(2).toInt.isInstanceOf[Int]) {
            throw new DonneesIncorrectesException(ErrorConst.ERROR_Tondeuse_coord_type_value(2))
          }
          if(!string.charAt(4).toInt.isInstanceOf[Int]) {
            throw new DonneesIncorrectesException(ErrorConst.ERROR_Tondeuse_coord_type_value(4))
          }
        }
      }
    }
    @throws(classOf[DonneesIncorrectesException[TondeuseInstruction]])
    def tondeuseInstructionEntries(string: String, emplacement: Int) {
      if (emplacement % 2 != 0) {
        throw new DonneesIncorrectesException(ErrorConst.ERROR_Tondeuse_instruction_emplacement)
      } else {
        if (!(string.contains('A') || string.contains('D') || string.contains('G'))) {
          throw new DonneesIncorrectesException(ErrorConst.ERROR_Tondeuse_instruction_type_value)
        }
      }
    }
  }

object ErrorConst {
  // Error common msg
  def ERROR_data_lenght(nbLine: Int): String ={
    var msg: String = nbLine match {
      case 0 => "Error > aucune donnée n'est renseigner"
      case nb if(nb < 3) => "Error > Il doit avoir au moins 3 ligne de donnée"
      case nb if(nb%2 == 0) => "Error > Verifier qu'Il ne manque pas une ligne de donnée"
    }
    msg
  }
  var ERROR_Pelouse: String = "Error > Pelouse entries > "
  var ERROR_Tondeuse_coord: String = "Error > Tondeuse coordonnee entries > "
  var ERROR_Tondeuse_instruction: String = "Error > Tondeuse instruction entries > "

  // Error msg d'entrée sur la pelouse
  var ERROR_Pelouse_Position: String = ERROR_Pelouse +  "Doit etre placé comme premier élément."
  var ERROR_Pelouse_lenght: String = ERROR_Pelouse +  "La Syntax ne respecte pas \'4 4\'."

  def ERROR_Pelouse_type_value(value: Int): String = {
    var nieme: String = value match {
      case 0 => "premiere"
      case 2 => "seconde"
      case  _=> ""
    }
    ERROR_Pelouse + "La "+ nieme + " donnée n'est pas entiere."
  }
  // Error msg d'entrée tondeuse coordonnée/instruction
  def ERROR_Tondeuse_coord_emplacement: String = ERROR_Tondeuse_coord +  "élément mal placé."
  def ERROR_Tondeuse_instruction_emplacement: String = ERROR_Tondeuse_instruction +  "élément mal placé."
  var ERROR_Tondeuse_coord_lenght: String = ERROR_Tondeuse_coord +  "La Syntax ne respecte pas \'1 2 N\'."
  def ERROR_Tondeuse_coord_type_value(value: Int): String = {
    var nieme: String = value match {
      case 0 => "premiere"
      case 2 => "seconde"
      case 4 => "troisieme"
      case  _=> ""
    }
    var resultError: String = ""
    if(value == 3) resultError =ERROR_Tondeuse_coord + "La "+ nieme + " donnée n'est pas une chaine de charactere."
    else resultError = ERROR_Tondeuse_coord + "La "+ nieme + " donnée n'est pas entiere."
    resultError
  }

  var ERROR_Tondeuse_instruction_type_value: String = ERROR_Pelouse + "l'entree ne peut contenir que des lettre " +
    "A, D, ou G"
}