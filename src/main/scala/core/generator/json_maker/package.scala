package core

import core.json_maker.JsValue

//Not used
package object json_maker {

  class JsValue(value: Any){
  }
  case class JsNumber(value:BigDecimal) extends JsValue {
    override def toString: String = {
      var str = value.toString()
      str
    }
  }
  case class JsString(value:String) extends JsValue {
    override def toString: String = {
      var str = "\"" + value.toString() + "\""
      str
    }
  }
  case class JsArray(value:IndexedSeq[JsValue] = Array[JsValue]()) extends JsValue{
    override def toString: String = {
      var str = "["
      value.foreach(e => e match {
        case jsStr:JsString => {
          str = str + e.toString()
        }
        case jsInt:JsNumber => {
          str = str + e.toString()
        }
        case jsObject:JsString => {
          str = str + e.toString()
        }
      })
      str + "]"
    }
  }
  case class JsObject(value:Map[String,JsValue]) extends JsValue{
    override def toString: String = {
      var str = "{"
      value.keys.foreach( k => {
        str = str + "\"" + k + "\":" + "\"" + value.get(k).toString() + "\","
        if(k == value.keys.size - 1) str + ","
      })
      str + "}"
    }
  }

  trait Writes[A] {
    def writes(value: A): JsValue
  }

  object Writes{
    implicit def apply[A](): Writes[A] = new Writes[A]() {
      override def writes(value: A): JsValue = Writes[A].writes(value)
    }
    //def apply[A](implicit a: Writes[A]): Writes[A] = A

    implicit val writesInt:Writes[Int] = new Writes[Int]() {
      override def writes(value: Int): JsValue = JsNumber(value)
    }
    implicit val writesString:Writes[String] = new Writes[String]() {
      override def writes(value: String): JsValue = JsString(value)
    }
  }
}

