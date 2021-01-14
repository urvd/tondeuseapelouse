import core.entree.{Entree, EntryPoints}
import core.generator.GeneratorToJson
import core.sortie.{EndPoint, Sortie}
import sun.security.util.Length

import scala.annotation.tailrec
import scala.io.Source

object Main {
  object Hello {
    def greeting(): String = "Hello world!"
  }
  def main(args: Array[String]): Unit = {
    println(Hello.greeting() + "\n");

    val file_root = "src/ressources/main/"
    println("# Lu en entrée depuis (\".src/ressources/main/entree.txt\":\n")
    val filename_entree = file_root + "entree.txt"

    var entries: EntryPoints =  Entree.read(filename_entree)

    println("\n# Resultat final traiter:")
    var end_result: EndPoint = Sortie.reponse(entries)
    end_result.show()

    println("\n# Générer en sortie vers (\".src/ressources/main/result.json\":")
    GeneratorToJson.generate(entries,end_result)


  }

}

//TODO: Lire via fichier de conf les données d'entrée. = \-\-\-\
//TODO: Impl structure de la tondeuse et pelouse. = \-\-\-\
//TODO: traitement et algo de deplacement de la tondeuse. = \-\-\-\
//TODO: Impl la structure de sortie en json.
//TODO: TU régulier. = \-\-\ \