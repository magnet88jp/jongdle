package jp.mydns.magnet.snippet

import _root_.scala.xml.{NodeSeq,Text}
import _root_.net.liftweb.http.{RequestVar,S,SHtml}
import _root_.net.liftweb.util.Helpers
import _root_.net.liftweb.common.{Box,Empty,Full,Failure}
import _root_.net.liftweb.http.js.JsCmds.RedirectTo
import S._
import Helpers._

import jp.mydns.magnet.model.Tile

/**
 * 麻雀牌の情報を表示するSnippet
 */
class TileSnippet{

  /**
   * 麻雀牌の一覧を表示します。
   */
  def list(xhtml: NodeSeq): NodeSeq = {
    // maxで30件を検索し、bind関数で一覧を出力
    Tile.findAll(30) flatMap { t =>
      bind( "tile", xhtml,
        "id" -> t.id.get,
        "name" -> t.name,
        "save" -> SHtml.link("save/" + t.id.get, () =>{} , Text("編集する") )
      )
    }
  }

  /**
   * 麻雀牌登録フォームの表示と、Submit時の保存を
   * 行います。
   */
  def save( xhtml:NodeSeq ) = {
//println("debug2")

    // urlからidを取得し、idが指定されていたら
    // データストアから検索する
    val tile =  S.param("id") match {
      case Full(id) =>  Tile.find( id.toLong )
      case Empty => None
      case Failure(msg, _, _ ) =>
        S.error( msg )
        None
    }

    // フォームの入力内容を保持するための変数
    val id = tile map{ _.id } getOrElse( None )
    var name = tile map{ _.name } getOrElse("")

//println("debug2:id=")
    // Submit時に呼び出されて、入力内容を元に
    // データストアへ保存する関数
    def doSave = {
      Tile.save(
        Tile( id, name )
      )
      redirectTo("/index") // 保存後はTopページへリダイレクト
    }

    // bind関数を利用してフォームを出力
    bind( "tile" , xhtml,
      "name" -> SHtml.text( name, name = _ ),
      "save" -> SHtml.submit( "Save", doSave _, "class"->"button")
    )
  }

}
