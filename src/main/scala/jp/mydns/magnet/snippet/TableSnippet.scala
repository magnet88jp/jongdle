package jp.mydns.magnet.snippet

import _root_.scala.xml.{NodeSeq,Text}
import _root_.net.liftweb.http.{RequestVar,S,SHtml}
import _root_.net.liftweb.util.Helpers
import _root_.net.liftweb.common.{Box,Empty,Full,Failure}
import _root_.net.liftweb.http.js.JsCmds._
import _root_.net.liftweb.http.js.JE._
import _root_.net.liftweb.http.SHtml._
import S._
import Helpers._
import scala.collection.JavaConversions._

import jp.mydns.magnet.model.Table
//import jp.mydns.magnet.model.Tile

/**
 * 麻雀牌の情報を表示するSnippet
 */
class TableSnippet{

  /**
   * 麻雀牌の一覧を表示します。
   */
  def list(xhtml: NodeSeq): NodeSeq = {
    // maxで30件を検索し、bind関数で一覧を出力
    Table.findAll(30) flatMap { t =>
      bind( "tile", xhtml,
        "id" -> t.id.get,
        "name" -> t.name,
        "save" -> SHtml.link("save/" + t.id, () =>{} , Text("編集する") )
      )
    }
  }

  def listEast(xhtml: NodeSeq): NodeSeq = {
    Table.listEast() flatMap { t =>
      bind( "tile", xhtml,
        "id" -> t.id.get,
        "name" -> t.name
      )
    }
  }

  def startMarjong(html: NodeSeq) : NodeSeq = {
    bind("marjong", html,
      "button" -> ajaxButton( Text( "開局"), {
        () => {
          println("Ajaxで開局")
          SetHtml("table-div", getEast())
//          SetHtml("button", Text("ツモ"))
        }
      })
    )
  }

  def getEast() = {
    <table>
      <tr><td>東場</td><td>一局</td></tr>
      <tr>
        <td>{Table.listEast() flatMap { t => <li>{t.name}</li>}}</td>
        <td>{Table.listSouth() flatMap { t => <li>{t.name}</li>}}</td>
        <td>{Table.listWest() flatMap { t => <li>{t.name}</li>}}</td>
        <td>{Table.listNorth() flatMap { t => <li>{t.name}</li>}}</td>
      </tr>
    </table>
  }

}
