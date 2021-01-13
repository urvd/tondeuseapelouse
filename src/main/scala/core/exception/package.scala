package core

import core.components.{ComponentImpl, Pelouse, TondeuseCoordonnee, TondeuseInstruction}

package object exception {
  class DonneesIncorrectesExceptionValidation {
    @throws(classOf[DonneesIncorrectesException[ComponentImpl]])
    def globalEntries(entrieslines: Int): Unit = {
      if (entrieslines < 3 || entrieslines % 2 == 0) {
        throw new DonneesIncorrectesException(ErrorType.ERROR_data_lenght(entrieslines))
      }
    }
    @throws(classOf[DonneesIncorrectesException[Pelouse]])
    def pelouseEntries(string: String, emplacement: Int): Unit = {
      if (emplacement != 0) {
        throw new DonneesIncorrectesException[Pelouse](ErrorType.ERROR_Pelouse_Position)
      } else {
        if (string.length() != 3) {
          throw new DonneesIncorrectesException[Pelouse](ErrorType.ERROR_Pelouse_lenght)
        } else {
          var verif1:Int = string.charAt(0).toInt
          var verif2:Int = string.charAt(2).toInt
          if (verif1 == 47 && verif2 == 47) {
            throw new DonneesIncorrectesException[Pelouse](ErrorType.ERROR_Pelouse_sans_taille)
          }
          if (verif1 <= 47 || verif1>=57) {
            throw new DonneesIncorrectesException[Pelouse](ErrorType.ERROR_Pelouse_type_value(0))
          }
          if (verif2 <= 47 || verif2 >= 57) {
            throw new DonneesIncorrectesException[Pelouse](ErrorType.ERROR_Pelouse_type_value(2))
          }
        }
      }
    }
    @throws(classOf[DonneesIncorrectesException[TondeuseCoordonnee]])
    def tondeuseCoordonneeEntries(string: String, emplacement: Int){
      if(emplacement%2 == 0){
        throw new DonneesIncorrectesException(ErrorType.ERROR_Tondeuse_coord_emplacement)
      }else{
        if(string.length() != 5){
          throw new DonneesIncorrectesException(ErrorType.ERROR_Pelouse_lenght)
        }else{
          var verif1:Int = string.charAt(0).toInt
          var verif2:Int = string.charAt(2).toInt
          if(verif1 <= 47 && verif1 >= 57) {
            throw new DonneesIncorrectesException(ErrorType.ERROR_Tondeuse_coord_type_value(0))
          }
          if(verif2 <= 47 && verif2 >= 57) {
            throw new DonneesIncorrectesException(ErrorType.ERROR_Tondeuse_coord_type_value(2))
          }
          var verif3 = (string.charAt(4).equals('N') || string.charAt(4).equals('S') || string.charAt(4).equals('E')
            || string.charAt(4).equals('O'))
          if(!verif3) {
            throw new DonneesIncorrectesException(ErrorType.ERROR_Tondeuse_coord_type_value(4))
          }
        }
      }
    }

    @throws(classOf[DonneesIncorrectesException[TondeuseInstruction]])
    def tondeuseInstructionEntries(string: String, emplacement: Int) {
      if (emplacement % 2 != 0) {
        throw new DonneesIncorrectesException(ErrorType.ERROR_Tondeuse_instruction_emplacement)
      } else {
        for(a <- string.toList if(!(a.equals('A') || a.equals('D') || a.equals('G'))) ){
          throw new DonneesIncorrectesException(ErrorType.ERROR_Tondeuse_instruction_type_value)
        }
      }
    }
  }
}
