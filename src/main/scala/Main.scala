import core.entree.{Entree, EntryPoints}
import core.generator.JsonGenerator
import core.sortie.Sortie
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
    println("# Lu en entrée:\n")
    val filename_entree = file_root + "entree.txt"

    var input =  Entree(filename_entree)
    var entries: EntryPoints = input.lecture()

    println("\n# Générer en sortie:")
    var output = Sortie(entries)
    var end_result = output.traitement()
    end_result.show()

    JsonGenerator.generate(entries,end_result)


  }

}

//TODO: Lire via fichier de conf les données d'entrée. = \-\-\-\
//TODO: Impl structure de la tondeuse et pelouse. = \-\-\-\
//TODO: traitement et algo de deplacement de la tondeuse. = \-\-\-\
//TODO: Impl la structure de sortie en json.
//TODO: TU régulier. = \-\-\ \