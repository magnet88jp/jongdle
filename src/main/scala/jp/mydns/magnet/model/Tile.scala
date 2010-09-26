package jp.mydns.magnet.model

import java.util.{ ArrayList => JList }
//import scala.collection.jcl.Conversions._
import scala.collection.JavaConversions._
import com.google.appengine.api.datastore._
import com.google.appengine.api.datastore.Query.{FilterOperator, SortDirection }

case class Tile(
  id:Option[Long],
  name:String )

object Tile {
  // GAEのLowLevelAPIを利用するためのDatastoreServiceを返します
  private def dsService =  DatastoreServiceFactory.getDatastoreService

  /**
   * Eventオブジェクトを保存します。
   */
  def save( tile:Tile ) = {
//println("debug:save")
//println("debug:save:tile.name="+tile.name)
    dsService.put( tile2Entity( tile ) )
//    var key = dsService.put( tile2Entity( tile ) )
//println("debug:key="+key)
//println("debug:keystr="+key.toString())
//println("debug:keyid="+key.getId())
//println("debug:keykind="+key.getKind())
//println("debug:keyname="+key.getName())
//println("debug:keyhash="+key.hashCode())
//findAll(30)
//var ee = find( key.getId )
//println("debug:findname="+ee)

  }

  /**
   * IDからentityを検索して返します。
   */
  def find( id:Long ) = {
    val key = KeyFactory.createKey("tile",  id)
    entity2Tile( dsService.get( key )) match{
      case null => None
      case e => Some(e)
    }
  }

  /**
   * Tileの情報を検索し、Listで返します。
   */
  def findAll(limit:Int) = {
//println("debug:findAll:limit="+limit)
//    val q = new Query( "tile").addSort("id",  SortDirection.ASCENDING)
    val q = new Query( "tile")

//println("debug2:afterquery")
    dsService.prepare( q ).asList(
//    var list = dsService.prepare( q ).asList(
      FetchOptions.Builder.withLimit( limit )
    ).map { e => entity2Tile( e ) }

//println("debug3:afterprepare")
//    val m = (1 to 5).toList
//    def pp(in: Int) = {println("debug in="+in)
//      true
//    }
//    m.filter(pp)
//    def pp2(t: Tile) = {println("debug2 t="+t.name)
//      true
//    }
//    list.map(n => println("debug:name="+n.name))
//    list.filter(pp2)
//println("debug4:aftermap")
//
//    list
  }

  /**
   * GAEのEnityがもつPropertyの値を型を指定してとりだします。
   */
  def entityValue[T]( e:Entity, name:String ) =
    e.getProperty( name ).asInstanceOf[T]

  /**
   * GAEのEntityオブジェクトからTileオブジェクトを作成します。
   */
  def entity2Tile( e:Entity ) = {
//println("debug3:entity2Tile:id="+e.getKey.getId)
    Tile(
      Some(e.getKey.getId),
      entityValue[String]( e, "name" )
    )
  }

  /**
   * TileオブジェクトからGAEのEntityオブジェクトを返します。
   */
  def tile2Entity( tile:Tile ) = {
//println("debug4:tile2Entity:name="+tile.name)
    val e = tile.id match {
      case Some(id) => new Entity( KeyFactory.createKey("tile", id) )
      case None => new Entity( "tile" )
    }
//println("debug4:tile2Entity:id="+e.getKey.getId)
    e.setProperty( "name", tile.name )
    e
  }

}
