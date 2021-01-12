import Main.Hello
import org.scalatest.funsuite.AnyFunSuite

class HelloSpec extends AnyFunSuite {

  test("The Hello object should say \"Hello world!\"") {
    assert(Hello.greeting === "Hello world!")
  }

  test(
    """ "Hello world!"(13) should throw a "java.lang.StringIndexOutOfBoundsException" """
  ) {
    assertThrows[java.lang.StringIndexOutOfBoundsException](Hello.greeting()(13))
  }

}
