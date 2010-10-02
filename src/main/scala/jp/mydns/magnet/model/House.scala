package jp.mydns.magnet.model

import java.util.{ ArrayList => JList }
import scala.collection.JavaConversions._
import com.google.appengine.api.datastore._
import com.google.appengine.api.datastore.Query.{FilterOperator, SortDirection }

class House ()

object House {
  var tehai:List[Int] = List()
  var kawa:List[Int] = List()
}
