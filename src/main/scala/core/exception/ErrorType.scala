package core.exception

object ErrorType {
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
  var ERROR_Pelouse_sans_taille = ERROR_Pelouse + "une pelouse de taille (0,0) n'est pas possible"
  // Error msg d'entrée tondeuse coordonnée/instruction
  def ERROR_Tondeuse_coord_emplacement: String = ERROR_Tondeuse_coord +  "élément mal placé."
  def ERROR_Tondeuse_instruction_emplacement: String = ERROR_Tondeuse_instruction +  "élément mal placé."
  var ERROR_Tondeuse_coord_lenght: String = ERROR_Tondeuse_coord +  "La Syntax ne respecte pas \'1 2 N\'."
  def ERROR_Tondeuse_coord_type_value(value: Int): String = {
    var resultError: String = value match {
      case 0 => ERROR_Tondeuse_coord + "La coordonnéé x (premiere donnée)  n'est pas une chaine de charactere."
      case 2 => ERROR_Tondeuse_coord + "La coordonnéé y (seconde donnée) n'est pas une chaine de charactere."
      case 4 => ERROR_Tondeuse_coord + "La troisieme donnée n'est pas entiere ou ne correspond pas a une " +
        "direction."
      case  _=> ""
    }
    resultError
  }

  var ERROR_Tondeuse_instruction_type_value: String = ERROR_Pelouse + "l'entree ne peut contenir que des lettre " +
    "A, D, ou G"
}
