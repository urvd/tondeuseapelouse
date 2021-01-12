import core.{DonneesIncorrectesExceptionValidation, Entree, EntryPoints}
import sun.security.util.Length

import scala.annotation.tailrec
import scala.io.Source

object Main {
  object Hello {
    def greeting(): String = "Hello world!"
  }
  def main(args: Array[String]): Unit = {
    println(Hello.greeting());

  val filename = "entree.txt"
    var input =  Entree(filename)
    var entries: EntryPoints = input.lecture()
  }

}

//TODO: Lire via fichier de conf les données d'entrée. = \-\-\-\
//TODO: Impl structure de la tondeuse et pelouse. =\-\-\\
//TODO: traitement et algo de deplacement de la tondeuse
//TODO: Impl la structure de sortie en json.
//TODO: TU régulier