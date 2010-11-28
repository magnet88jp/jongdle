package jp.mydns.magnet.snippet

import _root_.scala.xml._
import _root_.net.liftweb.http.{RequestVar,S,SHtml}
import _root_.net.liftweb.util.Helpers
import _root_.net.liftweb.util.Helpers._
import _root_.net.liftweb.common.{Box,Empty,Full,Failure}
import _root_.net.liftweb.http.js.JsCmds._
import _root_.net.liftweb.http.js.JE._
import _root_.net.liftweb.http.js._
import _root_.net.liftweb.http.SHtml._
import S._

class AjaxSandbox {

  def simple(xhtml: NodeSeq):NodeSeq = {
    bind("f", xhtml,
//      "trigger" -> SHtml.ajaxButton( "push" , doSimple _ )
      "trigger" -> <button onclick={SHtml.ajaxCall(Str("abc"), doSimple _)._2}>Press</button>
    )
  }

  def doSimple(s:String): JsCmd = {
    println( s + " passed by client")
    JsCmds.SetHtml( "placeholder", <h1>Ajax!:{s}</h1> )
  }

}

