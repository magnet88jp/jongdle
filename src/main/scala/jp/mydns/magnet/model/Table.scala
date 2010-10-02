package jp.mydns.magnet.model

import java.util.{ ArrayList => JList }
import scala.collection.JavaConversions._
import scala.util.Random
import com.google.appengine.api.datastore._
import com.google.appengine.api.datastore.Query.{FilterOperator, SortDirection }

case class Table(
  id:Option[Long],
  name:String )

object Table {

  // initialize yamaList
  def initYamaList(): List[String] = {
    def paiList[T](x: String, xs1: List[T], xs2: List[String]): List[String] = xs1 match {
      case List() => xs2
      case y :: xs3 => paiList( x, xs3, xs2 ::: List.make(4, y+x))
    }
    Random.shuffle(
      paiList("m", List.range(1, 10), List()) :::
      paiList("p", List.range(1, 10), List()) :::
      paiList("s", List.range(1, 10), List()) :::
      paiList("", List("ea", "so", "we", "no", "wh", "ha", "ch"), List())
    )
  }
  var yamaList = initYamaList()
println("yama="+yamaList.toString)

  def haipaiList(): List[String] = {
    val (haipai, yama) = yamaList splitAt 13
    yamaList = yama
    haipai
  }

  def eastList:  List[String] = haipaiList()
  def southList: List[String] = haipaiList()
  def westList:  List[String] = haipaiList()
  def northList: List[String] = haipaiList()

}

