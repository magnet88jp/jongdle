package jp.mydns.magnet.model

import java.util.{ ArrayList => JList }
//import scala.collection.jcl.Conversions._
import scala.collection.JavaConversions._
import scala.util.Random
import com.google.appengine.api.datastore._
import com.google.appengine.api.datastore.Query.{FilterOperator, SortDirection }

//import jp.mydns.magnet.model.Tile

case class Table(
  id:Option[Long],
  name:String )

object Table {
  // 136牌の配列定義
  var tiles:List[Tile] = List() 

  val su1 = Array( "m", "p", "s" )
  val su2 = 1 to 9
  val ji = Array( "ea", "so", "we", "no", "wh", "ha", "ch") 
  val num = 1 to 4

  var cnt = 0L;
  for( i <- su1; j <- su2; k <- num ) {
    tiles = tiles ::: List( Tile(Some(cnt), j + i) )
    cnt+=1;
  }
  for( i <- ji; k <- num ) {
    tiles = tiles ::: List( Tile(Some(cnt), i) )
    cnt+=1;
  }

  // ツモ順を表すランダムな0-135の配列
  val tsumo:List[Int] = Random.shuffle(List.range(0,135))
//  println("tsumo(0)="+tsumo(0))
//  println("tsumo(1)="+tsumo(1))
//  println("tsumo(2)="+tsumo(2))
//  println("tiles(10)="+tiles(10).toString)

//  val shipai:List[Int] = Random.shuffle(List.range(0,135))
//  var tsumos:List[Tile] = List()
//  for( i <- 0 to 135 ) {
//    println("i="+i)
//    println("tiles(shipai(i))="+tiles(shipai(i)))
////    tsumos = tsumos ::: List( Tile(Some(tiles(shipai(i)).getId), i ) )
//    tsumos = tsumos ::: List( tiles(shipai(i)) )
//  }  


  /**
   * IDからentityを検索して返します。
   */
  def find( id:Long ) = {
  }


  def isTsumo(tile:Tile) = {
    tile.id match {
      case Some(f) => (f == tsumo(0) || f == tsumo(1)  || f == tsumo(2) || f == tsumo(3) || f == tsumo(4) || f == tsumo(5) || f == tsumo(6) || f == tsumo(7) || f == tsumo(8) || f == tsumo(9) || f == tsumo(10) || f == tsumo(11) || f == tsumo(12))
      case None => false
    }
  }

  /**
   * Tileの情報を検索し、Listで返します。
   */
  def findAll(limit:Int) = {
//    tiles.toList
    // tsumoから13牌取得
//    tiles.filter(x => (x.id == Some(0L) || x.id == Some(1L) || x.id == Some(5L))).toList

    tiles.filter(isTsumo).toList
// tilesを最適化
//    tsumos.filter(isTsumo).toList

//    var ar:Array[Tile] = Array(); 
////    tsumos.slice(0,12).toList
//    tsumos.copyToArray(ar, 0, 12)
//    ar.toList
  }

  // 麻雀の開始
  def startMarjong() = {
    findAll(30)
  }

  // 東家の手牌を表示
  def listEast() = {
    findAll(13)
  }

  // 南家の手牌を表示
  def listSouth() = {
    findAll(13)
  }

  // 西家の手牌を表示
  def listWest() = {
    findAll(13)
  }

  // 北家の手牌を表示
  def listNorth() = {
    findAll(13)
  }

  // ツモ
  def getTile() = {
  }

  // 捨てる
  def releaseTile() = {
  }


}

