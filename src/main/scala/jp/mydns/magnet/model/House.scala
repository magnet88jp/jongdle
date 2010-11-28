package jp.mydns.magnet.model

import java.util.{ ArrayList => JList }
import scala.collection.JavaConversions._
import com.google.appengine.api.datastore._
import com.google.appengine.api.datastore.Query.{FilterOperator, SortDirection }

class House {
  var tehai: List[String] = List()
  var kawa:  List[String] = List()
  def setTehai( list: List[String] ) = tehai = list
  def getTehai: List[String] = tehai
//  def setTsumo(s: String) = tehai = tehai ::: List(s)
  def releasePai(s: String): List[String] = {
    tehai = tehai filter (_ != s)
    tehai
  }
  def addTsumo(list: List[String]) = tehai = tehai ::: list
  
}

object House {
}
