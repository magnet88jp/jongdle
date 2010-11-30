package jp.mydns.magnet.snippet

import _root_.scala.xml.{NodeSeq,Text}
import _root_.net.liftweb.http.{RequestVar,S,SHtml}
import _root_.net.liftweb.util.Helpers
import _root_.net.liftweb.common.{Box,Empty,Full,Failure}
import _root_.net.liftweb.http.js._
import _root_.net.liftweb.http.js.JsCmds._
import _root_.net.liftweb.http.js.JE._
import _root_.net.liftweb.http.SHtml._
import S._
import Helpers._
import scala.collection.JavaConversions._

import jp.mydns.magnet.model.Table

/**
 * 麻雀牌の情報を表示するSnippet
 */
class TableSnippet{

  def eastList(xhtml: NodeSeq): NodeSeq = <ul id="eastTehai">{
    if(Table.tsumoban%4 == 0) {
      Table.eastList flatMap { t => 
        bind( "tile", xhtml,
  // NG        "name" -> ajaxButton( Text(t), (t) => SetHtml("table-div",releaseEast(t)) )
  //        "name" -> a( Text(t), SetHtml("table-div",getEast) )
  //        "name" -> a( Text(t), SetHtml("table-div",releaseEast(t)) )
  //        "name" -> {if(Table.tsumoban%4 == 0)}<li><button onclick={SHtml.ajaxCall(Str(t), releaseEast _)._2}>{t}</button></li>{else}<li>{t}</li>
          "name" -> <li><button onclick={SHtml.ajaxCall(Str(t), releaseEast _)._2}>{t}</button></li>
  // OK        "name" -> <span onclick={SHtml.ajaxCall(Str(t), releaseEast _)._2}>{t}</span>
        )
      }
    } else {
      Table.eastList flatMap { t => 
        bind( "tile", xhtml, "name" -> <li>{t}</li>)
      }
    }
  }</ul>

  def southList(xhtml: NodeSeq): NodeSeq = <ul id="southTehai">{
    if(Table.tsumoban%4 == 1) {
      Table.southList flatMap { t => 
        bind( "tile", xhtml,
          "name" -> <li><button onclick={SHtml.ajaxCall(Str(t), releaseSouth _)._2}>{t}</button></li>
        )
      }
    } else {
      Table.southList flatMap { t => 
        bind( "tile", xhtml, "name" -> <li>{t}</li>)
      }
    }
  }</ul>

  def westList(xhtml: NodeSeq): NodeSeq = {
    Table.westList flatMap { t => bind( "tile", xhtml, "name" -> t) }
  }

  def northList(xhtml: NodeSeq): NodeSeq = {
    Table.northList flatMap { t => bind( "tile", xhtml, "name" -> t) }
  }

  def eastTsumo (xhtml: NodeSeq): NodeSeq = Table.eastTsumo  flatMap { t => bind( "tile", xhtml, "name" -> t) }
  def southTsumo(xhtml: NodeSeq): NodeSeq = Table.southTsumo flatMap { t => bind( "tile", xhtml, "name" -> t) }

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
        <td>{Table.eastList  flatMap { t => <li>{t}</li>}}</td>
        <td>{Table.southList flatMap { t => <li>{t}</li>}}</td>
        <td>{Table.westList  flatMap { t => <li>{t}</li>}}</td>
        <td>{Table.northList flatMap { t => <li>{t}</li>}}</td>
      </tr>
    </table>
  }

//  def releaseEast(s:String): JsCmd = {
//    JsCmds.SetHtml( "eastTehai", <table>
//      <tr><td>東場</td><td>一局</td></tr>
//      <tr>
//        <td>{Table.releaseEast(s) flatMap { t => <li>{t}</li>}}</td>
//      </tr>
//    </table>)
//  }
  def releaseEast(s:String): JsCmd = {
    JsCmds.SetHtml( "eastTehai", {Table.releaseEast(s) flatMap { t => <li>{t}</li>}})
  }
  def releaseSouth(s:String): JsCmd = {
    JsCmds.SetHtml( "southTehai", {Table.releaseSouth(s) flatMap { t => <li>{t}</li>}})
  }

}
